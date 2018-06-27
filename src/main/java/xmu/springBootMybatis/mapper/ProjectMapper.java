package xmu.springBootMybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import xmu.springBootMybatis.entity.Project;

@Mapper
public interface ProjectMapper {
	
	//获得已经完成的或者未完成的project
	@Select("select * from project where user_id = #{user_id} and finish_or_not=#{finish_or_not}")
	public List<Project> getProjectByType(@Param("finish_or_not")int finish_or_not,@Param("user_id")long user_id);
	
	@Select("select location from path where id=1")
	public String getPath();
	
	@Update("Update path set location= #{location} where id=1")
	public int changePath(@Param("location") String location);

	@Insert("insert into project(project_name,project_instruction,create_time,project_location,finish_or_not,word_segmentation,user_id) "
			+ "values(#{project.project_name},#{project.project_instruction},#{project.create_time},#{project.project_location},"
			+ "#{project.finish_or_not},#{project.word_segmentation},#{project.user_id})")
	@Options(useGeneratedKeys=true, keyProperty="project.id")
	public long createProject(@Param("project") Project project);

	@Update("update project set statistical_analysis=#{chooseNode},classification_algorithm=#{classification_algorithm},"
			+ "clustering_algorithm=#{clustering_algorithm},Kmeans_value=#{Kmeans_value}, SVM_value=#{SVM_value} where id=#{projectId}")
	public int updateProjectAnotherMethod(@Param("chooseNode") boolean chooseNode,
			@Param("classification_algorithm") String classification_algorithm,
			@Param("clustering_algorithm") String clustering_algorithm,@Param("projectId") long projectId,
			@Param("Kmeans_value") String Kmeans_value,@Param("SVM_value") String SVM_value);
	
	@Select("select * from project where id = #{projectId}")
	public Project getProjectById(@Param("projectId") long projectId);
	
	@Select("select name from word_segmentation_tool where id =#{id}")
	public String getWordSegmentationTool(@Param("id")int id);
	
	@Select("select name from classification_algorithm where id =#{id}")
	public String getClassification(@Param("id")int id);
	
	@Select("select name from clustering_algorithm where id =#{id}")
	public String getClustering(@Param("id")int id);
	
	//完全完成了
	@Update("Update project set finish_or_not= 2 where id=#{id}")
	public int changeFinishOrNotFinish(@Param("id")long id);
	
	//部分完成，返回的是修改了多少行
	@Update("Update project set finish_or_not=1 where id=#{id}")
	public int changeFinishOrNotHalfFinish(@Param("id")long id);
}
