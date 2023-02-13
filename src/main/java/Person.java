public class Person {
    private final String name;
    private final int age;
    private final String gender;    //Male, Female

    /**
     * Constructs a person with a name, age and gender
     *
     * @param name the name of the person
     * @param age the age of the person
     * @param gender the gender of the person
     */
    public Person(String name, int age, String gender){
        if(age <= 0 || age > 150){
            throw new IllegalArgumentException("Age must be an integer between 1 and 150");
        }else if(!gender.toLowerCase().equals("male") && !gender.toLowerCase().equals("female")){
            throw new IllegalArgumentException("Gender must be 'Male' or 'Female'");
        }

        this.name = name;
        this.age = age;
        this.gender = gender.substring(0, 1).toUpperCase() + gender.substring(1);
    }

    public String name(){ return name; }

    public int age(){ return age; }

    public String gender(){ return gender; }
}
