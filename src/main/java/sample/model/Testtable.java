package sample.model;

public class Testtable extends TesttableKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.testtable.name
     *
     * @mbggenerated Mon Nov 30 20:46:23 JST 2015
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.testtable.name
     *
     * @return the value of public.testtable.name
     *
     * @mbggenerated Mon Nov 30 20:46:23 JST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.testtable.name
     *
     * @param name the value for public.testtable.name
     *
     * @mbggenerated Mon Nov 30 20:46:23 JST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}