package com.example.demo.src.instructor;


import com.example.demo.src.instructor.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class InstructorDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){ this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public List<GetInstructorsRes> retrieveInstructors() {
        String getInstructorsQuery = "select * from Instructor";
        return this.jdbcTemplate.query(getInstructorsQuery,
                // 강사 idx, 이름, 경력, 소개, 프로필 사진 경로, List<지역>
                (rs,rowNum) -> new GetInstructorsRes(
                        rs.getInt("instrIdx"),
                        rs.getString("name"),
                        rs.getInt("experience"),
                        rs.getString("introducing"),
                        rs.getString("profileImgPath"),
                        this.jdbcTemplate.query("select * from Region where instrIdx=?;",
                                (rs1, rownum) -> new GetRegion (
                                        rs1.getString("region")
                                ), rs.getInt("instrIdx"))
                ));
    }

    public List<GetRegionInstrsRes> retrieveRegionInstructors (String region) {
        String getRegionInstrsQuery = "select *\n" +
                "from Instructor left join Region on Instructor.instrIdx=Region.instrIdx\n" +
                "where Region.region=?;";
        String getRegionInstrsParams = region;
        return this.jdbcTemplate.query(getRegionInstrsQuery,
                (rs, rowNum) -> new GetRegionInstrsRes(
                        rs.getInt("instrIdx"),
                        rs.getString("name"),
                        rs.getString("profileImgPath")
                ), getRegionInstrsParams);
    }

    public int checkRegionExists(String region) {
        try {
            String checkRegionExistsQuery = "select exists(select *\n" +
                    "from Region\n" +
                    "where region=?);";
            String checkRegionExistsParams = region;
            return this.jdbcTemplate.queryForObject(checkRegionExistsQuery, int.class, checkRegionExistsParams);
        } catch (Exception exception) {
            return 0;
        }
    }




}
