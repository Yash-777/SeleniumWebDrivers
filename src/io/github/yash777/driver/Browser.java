package io.github.yash777.driver;

/**
 * An enum type is a special data type 
 * that enables for a variable to be a set of predefined constants. The variable must 
 * be equal to one of the values that have been predefined for it.
 * 
 * <UL> All enums implicitly extend java.lang.Enum.
 * <LI> Enum type's fields are in UpperCase letters, As they are constants.
 * <LI> The enum declaration defines a class (called an enum type).
 * The enum class body can include methods and other Fields.
 * <LI> As You cannot invoke an enum constructor yourself. 
 * The constructor <B>Fields must be private accessed.</B>
 * <LI> The compiler automatically adds some <B>special methods</B> when it creates
 * an enum, Like:<P>EnumClass.class {getEnumConstants(), getFields(), isEnum(), ...}.
 * @author yashwanth.m
 * @see https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 * 
 */
public enum Browser {
	FIREFOX, CHROME, OPERA, IEXPLORE;
}