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


package edu.stanford.smi.protegex.owl.swrl.portability.p3;

import java.util.HashSet;
import java.util.Set;

import edu.stanford.smi.protegex.owl.swrl.portability.OWLClassReference;
import edu.stanford.smi.protegex.owl.swrl.portability.OWLPropertyReference;

/**
 * Class representing an OWL property
 */
public abstract class P3OWLPropertyReference implements OWLPropertyReference
{
	// There is an equals method defined on this class.
	private String propertyURI;
	private Set<OWLClassReference> domainClasses, rangeClasses;
	private Set<OWLPropertyReference> superProperties, subProperties, equivalentProperties;

	public P3OWLPropertyReference(String propertyURI)
	{
		this.propertyURI = propertyURI;
		initialize();
	}

	public void addDomainClass(OWLClassReference domainClass)
	{
		this.domainClasses.add(domainClass);
	}

	public void addRangeClass(OWLClassReference rangeClass)
	{
		this.rangeClasses.add(rangeClass);
	}

	public void addSuperProperty(OWLPropertyReference superProperty)
	{
		this.superProperties.add(superProperty);
	}

	public void addSubProperty(OWLPropertyReference subProperty)
	{
		this.subProperties.add(subProperty);
	}

	public void addEquivalentProperty(OWLPropertyReference equivalentProperty)
	{
		this.equivalentProperties.add(equivalentProperty);
	}

	public String getURI()
	{
		return propertyURI;
	}

	public Set<OWLClassReference> getDomainClasses()
	{
		return domainClasses;
	}

	public Set<OWLClassReference> getRangeClasses()
	{
		return rangeClasses;
	}

	public Set<OWLPropertyReference> getSuperProperties()
	{
		return superProperties;
	}

	public Set<OWLPropertyReference> getSubProperties()
	{
		return subProperties;
	}

	public Set<OWLPropertyReference> getEquivalentProperties()
	{
		return equivalentProperties;
	}

	public Set<OWLPropertyReference> getTypes()
	{
		Set<OWLPropertyReference> types = new HashSet<OWLPropertyReference>(superProperties);
		types.add(this);

		return types;
	}

	public String toString()
	{
		return getURI();
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		P3OWLPropertyReference impl = (P3OWLPropertyReference)obj;
		return (getURI() == impl.getURI() || (getURI() != null && getURI().equals(impl.getURI())));
	}

	public int hashCode()
	{
		int hash = 767;

		hash = hash + (null == getURI() ? 0 : getURI().hashCode());

		return hash;
	}

	private void initialize()
	{
		domainClasses = new HashSet<OWLClassReference>();
		rangeClasses = new HashSet<OWLClassReference>();
		superProperties = new HashSet<OWLPropertyReference>();
		subProperties = new HashSet<OWLPropertyReference>();
		equivalentProperties = new HashSet<OWLPropertyReference>();
	}

}
