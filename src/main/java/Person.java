import java.util.List;

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

    /**
     * Computes the average age of male and female people in a list and returns the result in
     * an array of two elements (the first element is the male mean age and the second one is the
     * female mean age)
     *
     * @param persons a not null array with different people
     * @return an array with two elements, where the first one is the mean for men and the second one for women. In case
     * there are no men or women, the mean will be -1
     */
    public double[] averageAgePerGender(List<Person> persons){
        if(persons == null){ throw new IllegalArgumentException("not expecting null parameter"); }

        double[] res = {0.0,0.0};
        int[] count = {0,0};

        for(Person p : persons){
            int pos = p.gender().equals("Male") ? 0 : 1;
            res[pos] += p.age();
            count[pos]++;
        }

        for(int i = 0; i < 2; i++){
            if(count[i] == 0){
                res[i] = -1;
            }else {
                res[i] = res[i] / (double)count[i];
            }
        }

        return res;
    }
}
