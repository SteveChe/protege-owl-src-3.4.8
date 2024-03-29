/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License");  you may not use this file except in 
 * compliance with the License.  You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * The Original Code is Protege-2000.
 *
 * The Initial Developer of the Original Code is Stanford University. Portions
 * created by Stanford University are Copyright (C) 2007.  All Rights Reserved.
 *
 * Protege was developed by Stanford Medical Informatics
 * (http://www.smi.stanford.edu) at the Stanford University School of Medicine
 * with support from the National Library of Medicine, the National Science
 * Foundation, and the Defense Advanced Research Projects Agency.  Current
 * information about Protege can be obtained at http://protege.stanford.edu.
 *
 */


package edu.stanford.smi.protegex.owl.swrl.bridge.builtins.temporal;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import edu.stanford.smi.protegex.owl.swrl.bridge.builtins.temporal.exceptions.TemporalException;

/**
 * A class supporting processing of datetime strings represented in the standard XML Schema date format 'yyyy-MM-ddTHH:mm:ss.S'.
 * <p>
 * Timezone offsets are not yet supported.
 */
public class XSDDatetimeStringProcessor extends DatetimeStringProcessor
{
  private static SimpleDateFormat _dateFormat = new SimpleDateFormat("y-M-d'T'h:m:s.S"); // TODO: No support for Z yet
  private static String _delimiters = "-:.TZ";
  private GregorianCalendar gc = new GregorianCalendar();

  // The number of tokens (including delimeters) necessary to strip a datetime to a specified granularity.
  private  static int[] _gTokenIndex = { 1, 3, 5, 7, 9, 11, 13 }; // 1=YEARS, 3=MONTHS etc.

  // Strings to pad a partially specified datetime.
  private static String _datetimeRoundDownPadding[] = {"-01-01T00:00:00.000","-01T00:00:00.000","T00:00:00.000",":00:00.000",":00.000",".000", ""};

  // Day-in-month must be dealt with separately.
  private static String _datetimeRoundUpPadding[] = { "-12-31T23:59:59.999", "", "T23:59:59.999", ":59:59.999", ":59.999", ".999", "" };

  public XSDDatetimeStringProcessor()
  {
    super(_dateFormat, _delimiters, _gTokenIndex, _datetimeRoundDownPadding, _datetimeRoundUpPadding);
  } 

  protected String constructDatetimeStringFromMillisecondsFrom1970Count(long millisecondsFrom1970) throws TemporalException
  {
    Timestamp ts= new Timestamp(millisecondsFrom1970);
    TimeZone tz = gc.getTimeZone();
    
    if (tz.inDaylightTime(ts)) 
    	ts = new Timestamp(millisecondsFrom1970 - Temporal.daylightSavingsTimeOffsetInMillis);
        
    return ts.toString().replace(' ', 'T'); // Timestamp.toString returns in JDBC format so replace space with 'T'.
  } 
} 
