package sample.mapper;

import java.util.List;
import sample.model.SchemaVersion;
import sample.model.SchemaVersionExample;
import sample.model.SchemaVersionKey;

public interface SchemaVersionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Mon Nov 30 20:46:23 JST 2015
     */
    int deleteByPrimaryKey(SchemaVersionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Mon Nov 30 20:46:23 JST 2015
     */
    int insert(SchemaVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Mon Nov 30 20:46:23 JST 2015
     */
    int insertSelective(SchemaVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Mon Nov 30 20:46:23 JST 2015
     */
    List<SchemaVersion> selectByExample(SchemaVersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Mon Nov 30 20:46:23 JST 2015
     */
    SchemaVersion selectByPrimaryKey(SchemaVersionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Mon Nov 30 20:46:23 JST 2015
     */
    int updateByPrimaryKeySelective(SchemaVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.schema_version
     *
     * @mbggenerated Mon Nov 30 20:46:23 JST 2015
     */
    int updateByPrimaryKey(SchemaVersion record);
}