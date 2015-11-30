package sample.model;

import java.util.Date;

public class SchemaVersion extends SchemaVersionKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.schema_version.version_rank
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    private Integer versionRank;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.schema_version.installed_rank
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    private Integer installedRank;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.schema_version.description
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.schema_version.type
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.schema_version.script
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    private String script;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.schema_version.checksum
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    private Integer checksum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.schema_version.installed_by
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    private String installedBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.schema_version.installed_on
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    private Date installedOn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.schema_version.execution_time
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    private Integer executionTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.schema_version.success
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    private Boolean success;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.schema_version.version_rank
     *
     * @return the value of public.schema_version.version_rank
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public Integer getVersionRank() {
        return versionRank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.schema_version.version_rank
     *
     * @param versionRank the value for public.schema_version.version_rank
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public void setVersionRank(Integer versionRank) {
        this.versionRank = versionRank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.schema_version.installed_rank
     *
     * @return the value of public.schema_version.installed_rank
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public Integer getInstalledRank() {
        return installedRank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.schema_version.installed_rank
     *
     * @param installedRank the value for public.schema_version.installed_rank
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public void setInstalledRank(Integer installedRank) {
        this.installedRank = installedRank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.schema_version.description
     *
     * @return the value of public.schema_version.description
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.schema_version.description
     *
     * @param description the value for public.schema_version.description
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.schema_version.type
     *
     * @return the value of public.schema_version.type
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.schema_version.type
     *
     * @param type the value for public.schema_version.type
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.schema_version.script
     *
     * @return the value of public.schema_version.script
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public String getScript() {
        return script;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.schema_version.script
     *
     * @param script the value for public.schema_version.script
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public void setScript(String script) {
        this.script = script == null ? null : script.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.schema_version.checksum
     *
     * @return the value of public.schema_version.checksum
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public Integer getChecksum() {
        return checksum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.schema_version.checksum
     *
     * @param checksum the value for public.schema_version.checksum
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.schema_version.installed_by
     *
     * @return the value of public.schema_version.installed_by
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public String getInstalledBy() {
        return installedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.schema_version.installed_by
     *
     * @param installedBy the value for public.schema_version.installed_by
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public void setInstalledBy(String installedBy) {
        this.installedBy = installedBy == null ? null : installedBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.schema_version.installed_on
     *
     * @return the value of public.schema_version.installed_on
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public Date getInstalledOn() {
        return installedOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.schema_version.installed_on
     *
     * @param installedOn the value for public.schema_version.installed_on
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public void setInstalledOn(Date installedOn) {
        this.installedOn = installedOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.schema_version.execution_time
     *
     * @return the value of public.schema_version.execution_time
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public Integer getExecutionTime() {
        return executionTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.schema_version.execution_time
     *
     * @param executionTime the value for public.schema_version.execution_time
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.schema_version.success
     *
     * @return the value of public.schema_version.success
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.schema_version.success
     *
     * @param success the value for public.schema_version.success
     *
     * @mbggenerated Mon Nov 30 18:16:39 JST 2015
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }
}