/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cn.edu.pku.ss.matchmaker.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QoS implements org.apache.thrift.TBase<QoS, QoS._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("QoS");

  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.BOOL, (short)2);
  private static final org.apache.thrift.protocol.TField WEIGHT_FIELD_DESC = new org.apache.thrift.protocol.TField("weight", org.apache.thrift.protocol.TType.DOUBLE, (short)3);
  private static final org.apache.thrift.protocol.TField POINT_OF_SUPPLY_FIELD_DESC = new org.apache.thrift.protocol.TField("pointOfSupply", org.apache.thrift.protocol.TType.DOUBLE, (short)4);
  private static final org.apache.thrift.protocol.TField POINT_OF_VALUATION_FIELD_DESC = new org.apache.thrift.protocol.TField("pointOfValuation", org.apache.thrift.protocol.TType.DOUBLE, (short)5);
  private static final org.apache.thrift.protocol.TField POINT_OF_RETURN_FIELD_DESC = new org.apache.thrift.protocol.TField("pointOfReturn", org.apache.thrift.protocol.TType.DOUBLE, (short)6);
  private static final org.apache.thrift.protocol.TField RETURN_POINT_FIELD_DESC = new org.apache.thrift.protocol.TField("returnPoint", org.apache.thrift.protocol.TType.STRUCT, (short)7);
  private static final org.apache.thrift.protocol.TField POINT_ALL_FIELD_DESC = new org.apache.thrift.protocol.TField("pointAll", org.apache.thrift.protocol.TType.DOUBLE, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new QoSStandardSchemeFactory());
    schemes.put(TupleScheme.class, new QoSTupleSchemeFactory());
  }

  public String name; // optional
  public boolean type; // optional
  public double weight; // optional
  public double pointOfSupply; // optional
  public double pointOfValuation; // optional
  public double pointOfReturn; // optional
  public ReturnPoint returnPoint; // optional
  public double pointAll; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NAME((short)1, "name"),
    TYPE((short)2, "type"),
    WEIGHT((short)3, "weight"),
    POINT_OF_SUPPLY((short)4, "pointOfSupply"),
    POINT_OF_VALUATION((short)5, "pointOfValuation"),
    POINT_OF_RETURN((short)6, "pointOfReturn"),
    RETURN_POINT((short)7, "returnPoint"),
    POINT_ALL((short)8, "pointAll");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NAME
          return NAME;
        case 2: // TYPE
          return TYPE;
        case 3: // WEIGHT
          return WEIGHT;
        case 4: // POINT_OF_SUPPLY
          return POINT_OF_SUPPLY;
        case 5: // POINT_OF_VALUATION
          return POINT_OF_VALUATION;
        case 6: // POINT_OF_RETURN
          return POINT_OF_RETURN;
        case 7: // RETURN_POINT
          return RETURN_POINT;
        case 8: // POINT_ALL
          return POINT_ALL;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __TYPE_ISSET_ID = 0;
  private static final int __WEIGHT_ISSET_ID = 1;
  private static final int __POINTOFSUPPLY_ISSET_ID = 2;
  private static final int __POINTOFVALUATION_ISSET_ID = 3;
  private static final int __POINTOFRETURN_ISSET_ID = 4;
  private static final int __POINTALL_ISSET_ID = 5;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.NAME,_Fields.TYPE,_Fields.WEIGHT,_Fields.POINT_OF_SUPPLY,_Fields.POINT_OF_VALUATION,_Fields.POINT_OF_RETURN,_Fields.RETURN_POINT,_Fields.POINT_ALL};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.WEIGHT, new org.apache.thrift.meta_data.FieldMetaData("weight", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.POINT_OF_SUPPLY, new org.apache.thrift.meta_data.FieldMetaData("pointOfSupply", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.POINT_OF_VALUATION, new org.apache.thrift.meta_data.FieldMetaData("pointOfValuation", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.POINT_OF_RETURN, new org.apache.thrift.meta_data.FieldMetaData("pointOfReturn", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.RETURN_POINT, new org.apache.thrift.meta_data.FieldMetaData("returnPoint", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ReturnPoint.class)));
    tmpMap.put(_Fields.POINT_ALL, new org.apache.thrift.meta_data.FieldMetaData("pointAll", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(QoS.class, metaDataMap);
  }

  public QoS() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public QoS(QoS other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetName()) {
      this.name = other.name;
    }
    this.type = other.type;
    this.weight = other.weight;
    this.pointOfSupply = other.pointOfSupply;
    this.pointOfValuation = other.pointOfValuation;
    this.pointOfReturn = other.pointOfReturn;
    if (other.isSetReturnPoint()) {
      this.returnPoint = new ReturnPoint(other.returnPoint);
    }
    this.pointAll = other.pointAll;
  }

  public QoS deepCopy() {
    return new QoS(this);
  }

  @Override
  public void clear() {
    this.name = null;
    setTypeIsSet(false);
    this.type = false;
    setWeightIsSet(false);
    this.weight = 0.0;
    setPointOfSupplyIsSet(false);
    this.pointOfSupply = 0.0;
    setPointOfValuationIsSet(false);
    this.pointOfValuation = 0.0;
    setPointOfReturnIsSet(false);
    this.pointOfReturn = 0.0;
    this.returnPoint = null;
    setPointAllIsSet(false);
    this.pointAll = 0.0;
  }

  public String getName() {
    return this.name;
  }

  public QoS setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public boolean isType() {
    return this.type;
  }

  public QoS setType(boolean type) {
    this.type = type;
    setTypeIsSet(true);
    return this;
  }

  public void unsetType() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TYPE_ISSET_ID);
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return EncodingUtils.testBit(__isset_bitfield, __TYPE_ISSET_ID);
  }

  public void setTypeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TYPE_ISSET_ID, value);
  }

  public double getWeight() {
    return this.weight;
  }

  public QoS setWeight(double weight) {
    this.weight = weight;
    setWeightIsSet(true);
    return this;
  }

  public void unsetWeight() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __WEIGHT_ISSET_ID);
  }

  /** Returns true if field weight is set (has been assigned a value) and false otherwise */
  public boolean isSetWeight() {
    return EncodingUtils.testBit(__isset_bitfield, __WEIGHT_ISSET_ID);
  }

  public void setWeightIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __WEIGHT_ISSET_ID, value);
  }

  public double getPointOfSupply() {
    return this.pointOfSupply;
  }

  public QoS setPointOfSupply(double pointOfSupply) {
    this.pointOfSupply = pointOfSupply;
    setPointOfSupplyIsSet(true);
    return this;
  }

  public void unsetPointOfSupply() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __POINTOFSUPPLY_ISSET_ID);
  }

  /** Returns true if field pointOfSupply is set (has been assigned a value) and false otherwise */
  public boolean isSetPointOfSupply() {
    return EncodingUtils.testBit(__isset_bitfield, __POINTOFSUPPLY_ISSET_ID);
  }

  public void setPointOfSupplyIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __POINTOFSUPPLY_ISSET_ID, value);
  }

  public double getPointOfValuation() {
    return this.pointOfValuation;
  }

  public QoS setPointOfValuation(double pointOfValuation) {
    this.pointOfValuation = pointOfValuation;
    setPointOfValuationIsSet(true);
    return this;
  }

  public void unsetPointOfValuation() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __POINTOFVALUATION_ISSET_ID);
  }

  /** Returns true if field pointOfValuation is set (has been assigned a value) and false otherwise */
  public boolean isSetPointOfValuation() {
    return EncodingUtils.testBit(__isset_bitfield, __POINTOFVALUATION_ISSET_ID);
  }

  public void setPointOfValuationIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __POINTOFVALUATION_ISSET_ID, value);
  }

  public double getPointOfReturn() {
    return this.pointOfReturn;
  }

  public QoS setPointOfReturn(double pointOfReturn) {
    this.pointOfReturn = pointOfReturn;
    setPointOfReturnIsSet(true);
    return this;
  }

  public void unsetPointOfReturn() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __POINTOFRETURN_ISSET_ID);
  }

  /** Returns true if field pointOfReturn is set (has been assigned a value) and false otherwise */
  public boolean isSetPointOfReturn() {
    return EncodingUtils.testBit(__isset_bitfield, __POINTOFRETURN_ISSET_ID);
  }

  public void setPointOfReturnIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __POINTOFRETURN_ISSET_ID, value);
  }

  public ReturnPoint getReturnPoint() {
    return this.returnPoint;
  }

  public QoS setReturnPoint(ReturnPoint returnPoint) {
    this.returnPoint = returnPoint;
    return this;
  }

  public void unsetReturnPoint() {
    this.returnPoint = null;
  }

  /** Returns true if field returnPoint is set (has been assigned a value) and false otherwise */
  public boolean isSetReturnPoint() {
    return this.returnPoint != null;
  }

  public void setReturnPointIsSet(boolean value) {
    if (!value) {
      this.returnPoint = null;
    }
  }

  public double getPointAll() {
    return this.pointAll;
  }

  public QoS setPointAll(double pointAll) {
    this.pointAll = pointAll;
    setPointAllIsSet(true);
    return this;
  }

  public void unsetPointAll() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __POINTALL_ISSET_ID);
  }

  /** Returns true if field pointAll is set (has been assigned a value) and false otherwise */
  public boolean isSetPointAll() {
    return EncodingUtils.testBit(__isset_bitfield, __POINTALL_ISSET_ID);
  }

  public void setPointAllIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __POINTALL_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((Boolean)value);
      }
      break;

    case WEIGHT:
      if (value == null) {
        unsetWeight();
      } else {
        setWeight((Double)value);
      }
      break;

    case POINT_OF_SUPPLY:
      if (value == null) {
        unsetPointOfSupply();
      } else {
        setPointOfSupply((Double)value);
      }
      break;

    case POINT_OF_VALUATION:
      if (value == null) {
        unsetPointOfValuation();
      } else {
        setPointOfValuation((Double)value);
      }
      break;

    case POINT_OF_RETURN:
      if (value == null) {
        unsetPointOfReturn();
      } else {
        setPointOfReturn((Double)value);
      }
      break;

    case RETURN_POINT:
      if (value == null) {
        unsetReturnPoint();
      } else {
        setReturnPoint((ReturnPoint)value);
      }
      break;

    case POINT_ALL:
      if (value == null) {
        unsetPointAll();
      } else {
        setPointAll((Double)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME:
      return getName();

    case TYPE:
      return Boolean.valueOf(isType());

    case WEIGHT:
      return Double.valueOf(getWeight());

    case POINT_OF_SUPPLY:
      return Double.valueOf(getPointOfSupply());

    case POINT_OF_VALUATION:
      return Double.valueOf(getPointOfValuation());

    case POINT_OF_RETURN:
      return Double.valueOf(getPointOfReturn());

    case RETURN_POINT:
      return getReturnPoint();

    case POINT_ALL:
      return Double.valueOf(getPointAll());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NAME:
      return isSetName();
    case TYPE:
      return isSetType();
    case WEIGHT:
      return isSetWeight();
    case POINT_OF_SUPPLY:
      return isSetPointOfSupply();
    case POINT_OF_VALUATION:
      return isSetPointOfValuation();
    case POINT_OF_RETURN:
      return isSetPointOfReturn();
    case RETURN_POINT:
      return isSetReturnPoint();
    case POINT_ALL:
      return isSetPointAll();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof QoS)
      return this.equals((QoS)that);
    return false;
  }

  public boolean equals(QoS that) {
    if (that == null)
      return false;

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (this.type != that.type)
        return false;
    }

    boolean this_present_weight = true && this.isSetWeight();
    boolean that_present_weight = true && that.isSetWeight();
    if (this_present_weight || that_present_weight) {
      if (!(this_present_weight && that_present_weight))
        return false;
      if (this.weight != that.weight)
        return false;
    }

    boolean this_present_pointOfSupply = true && this.isSetPointOfSupply();
    boolean that_present_pointOfSupply = true && that.isSetPointOfSupply();
    if (this_present_pointOfSupply || that_present_pointOfSupply) {
      if (!(this_present_pointOfSupply && that_present_pointOfSupply))
        return false;
      if (this.pointOfSupply != that.pointOfSupply)
        return false;
    }

    boolean this_present_pointOfValuation = true && this.isSetPointOfValuation();
    boolean that_present_pointOfValuation = true && that.isSetPointOfValuation();
    if (this_present_pointOfValuation || that_present_pointOfValuation) {
      if (!(this_present_pointOfValuation && that_present_pointOfValuation))
        return false;
      if (this.pointOfValuation != that.pointOfValuation)
        return false;
    }

    boolean this_present_pointOfReturn = true && this.isSetPointOfReturn();
    boolean that_present_pointOfReturn = true && that.isSetPointOfReturn();
    if (this_present_pointOfReturn || that_present_pointOfReturn) {
      if (!(this_present_pointOfReturn && that_present_pointOfReturn))
        return false;
      if (this.pointOfReturn != that.pointOfReturn)
        return false;
    }

    boolean this_present_returnPoint = true && this.isSetReturnPoint();
    boolean that_present_returnPoint = true && that.isSetReturnPoint();
    if (this_present_returnPoint || that_present_returnPoint) {
      if (!(this_present_returnPoint && that_present_returnPoint))
        return false;
      if (!this.returnPoint.equals(that.returnPoint))
        return false;
    }

    boolean this_present_pointAll = true && this.isSetPointAll();
    boolean that_present_pointAll = true && that.isSetPointAll();
    if (this_present_pointAll || that_present_pointAll) {
      if (!(this_present_pointAll && that_present_pointAll))
        return false;
      if (this.pointAll != that.pointAll)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(QoS other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    QoS typedOther = (QoS)other;

    lastComparison = Boolean.valueOf(isSetName()).compareTo(typedOther.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, typedOther.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetType()).compareTo(typedOther.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, typedOther.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWeight()).compareTo(typedOther.isSetWeight());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWeight()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.weight, typedOther.weight);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPointOfSupply()).compareTo(typedOther.isSetPointOfSupply());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPointOfSupply()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pointOfSupply, typedOther.pointOfSupply);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPointOfValuation()).compareTo(typedOther.isSetPointOfValuation());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPointOfValuation()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pointOfValuation, typedOther.pointOfValuation);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPointOfReturn()).compareTo(typedOther.isSetPointOfReturn());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPointOfReturn()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pointOfReturn, typedOther.pointOfReturn);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetReturnPoint()).compareTo(typedOther.isSetReturnPoint());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReturnPoint()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.returnPoint, typedOther.returnPoint);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPointAll()).compareTo(typedOther.isSetPointAll());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPointAll()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pointAll, typedOther.pointAll);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("QoS(");
    boolean first = true;

    if (isSetName()) {
      sb.append("name:");
      if (this.name == null) {
        sb.append("null");
      } else {
        sb.append(this.name);
      }
      first = false;
    }
    if (isSetType()) {
      if (!first) sb.append(", ");
      sb.append("type:");
      sb.append(this.type);
      first = false;
    }
    if (isSetWeight()) {
      if (!first) sb.append(", ");
      sb.append("weight:");
      sb.append(this.weight);
      first = false;
    }
    if (isSetPointOfSupply()) {
      if (!first) sb.append(", ");
      sb.append("pointOfSupply:");
      sb.append(this.pointOfSupply);
      first = false;
    }
    if (isSetPointOfValuation()) {
      if (!first) sb.append(", ");
      sb.append("pointOfValuation:");
      sb.append(this.pointOfValuation);
      first = false;
    }
    if (isSetPointOfReturn()) {
      if (!first) sb.append(", ");
      sb.append("pointOfReturn:");
      sb.append(this.pointOfReturn);
      first = false;
    }
    if (isSetReturnPoint()) {
      if (!first) sb.append(", ");
      sb.append("returnPoint:");
      if (this.returnPoint == null) {
        sb.append("null");
      } else {
        sb.append(this.returnPoint);
      }
      first = false;
    }
    if (isSetPointAll()) {
      if (!first) sb.append(", ");
      sb.append("pointAll:");
      sb.append(this.pointAll);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (returnPoint != null) {
      returnPoint.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class QoSStandardSchemeFactory implements SchemeFactory {
    public QoSStandardScheme getScheme() {
      return new QoSStandardScheme();
    }
  }

  private static class QoSStandardScheme extends StandardScheme<QoS> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, QoS struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.type = iprot.readBool();
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // WEIGHT
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.weight = iprot.readDouble();
              struct.setWeightIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // POINT_OF_SUPPLY
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.pointOfSupply = iprot.readDouble();
              struct.setPointOfSupplyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // POINT_OF_VALUATION
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.pointOfValuation = iprot.readDouble();
              struct.setPointOfValuationIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // POINT_OF_RETURN
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.pointOfReturn = iprot.readDouble();
              struct.setPointOfReturnIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // RETURN_POINT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.returnPoint = new ReturnPoint();
              struct.returnPoint.read(iprot);
              struct.setReturnPointIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // POINT_ALL
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.pointAll = iprot.readDouble();
              struct.setPointAllIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, QoS struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.name != null) {
        if (struct.isSetName()) {
          oprot.writeFieldBegin(NAME_FIELD_DESC);
          oprot.writeString(struct.name);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetType()) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeBool(struct.type);
        oprot.writeFieldEnd();
      }
      if (struct.isSetWeight()) {
        oprot.writeFieldBegin(WEIGHT_FIELD_DESC);
        oprot.writeDouble(struct.weight);
        oprot.writeFieldEnd();
      }
      if (struct.isSetPointOfSupply()) {
        oprot.writeFieldBegin(POINT_OF_SUPPLY_FIELD_DESC);
        oprot.writeDouble(struct.pointOfSupply);
        oprot.writeFieldEnd();
      }
      if (struct.isSetPointOfValuation()) {
        oprot.writeFieldBegin(POINT_OF_VALUATION_FIELD_DESC);
        oprot.writeDouble(struct.pointOfValuation);
        oprot.writeFieldEnd();
      }
      if (struct.isSetPointOfReturn()) {
        oprot.writeFieldBegin(POINT_OF_RETURN_FIELD_DESC);
        oprot.writeDouble(struct.pointOfReturn);
        oprot.writeFieldEnd();
      }
      if (struct.returnPoint != null) {
        if (struct.isSetReturnPoint()) {
          oprot.writeFieldBegin(RETURN_POINT_FIELD_DESC);
          struct.returnPoint.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetPointAll()) {
        oprot.writeFieldBegin(POINT_ALL_FIELD_DESC);
        oprot.writeDouble(struct.pointAll);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class QoSTupleSchemeFactory implements SchemeFactory {
    public QoSTupleScheme getScheme() {
      return new QoSTupleScheme();
    }
  }

  private static class QoSTupleScheme extends TupleScheme<QoS> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, QoS struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetName()) {
        optionals.set(0);
      }
      if (struct.isSetType()) {
        optionals.set(1);
      }
      if (struct.isSetWeight()) {
        optionals.set(2);
      }
      if (struct.isSetPointOfSupply()) {
        optionals.set(3);
      }
      if (struct.isSetPointOfValuation()) {
        optionals.set(4);
      }
      if (struct.isSetPointOfReturn()) {
        optionals.set(5);
      }
      if (struct.isSetReturnPoint()) {
        optionals.set(6);
      }
      if (struct.isSetPointAll()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetType()) {
        oprot.writeBool(struct.type);
      }
      if (struct.isSetWeight()) {
        oprot.writeDouble(struct.weight);
      }
      if (struct.isSetPointOfSupply()) {
        oprot.writeDouble(struct.pointOfSupply);
      }
      if (struct.isSetPointOfValuation()) {
        oprot.writeDouble(struct.pointOfValuation);
      }
      if (struct.isSetPointOfReturn()) {
        oprot.writeDouble(struct.pointOfReturn);
      }
      if (struct.isSetReturnPoint()) {
        struct.returnPoint.write(oprot);
      }
      if (struct.isSetPointAll()) {
        oprot.writeDouble(struct.pointAll);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, QoS struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.type = iprot.readBool();
        struct.setTypeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.weight = iprot.readDouble();
        struct.setWeightIsSet(true);
      }
      if (incoming.get(3)) {
        struct.pointOfSupply = iprot.readDouble();
        struct.setPointOfSupplyIsSet(true);
      }
      if (incoming.get(4)) {
        struct.pointOfValuation = iprot.readDouble();
        struct.setPointOfValuationIsSet(true);
      }
      if (incoming.get(5)) {
        struct.pointOfReturn = iprot.readDouble();
        struct.setPointOfReturnIsSet(true);
      }
      if (incoming.get(6)) {
        struct.returnPoint = new ReturnPoint();
        struct.returnPoint.read(iprot);
        struct.setReturnPointIsSet(true);
      }
      if (incoming.get(7)) {
        struct.pointAll = iprot.readDouble();
        struct.setPointAllIsSet(true);
      }
    }
  }

}

