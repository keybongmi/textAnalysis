package xmu.springBootMybatis.entity;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Project implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String project_name;
	private String project_instruction;
	private String create_time;
	private String project_location;
	private short finish_or_not;//0代表没有进行分词，分类；1代表没有进行分类，但进行了分词，2代表进行了分类和分词
	private String classification_algorithm;
	private boolean statistical_analysis;
	private String word_segmentation;
	private String clustering_algorithm;
	private String Kmeans_value;
	private String SVM_value;
	
	
	private long user_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_instruction() {
		return project_instruction;
	}
	public void setProject_instruction(String project_instruction) {
		this.project_instruction = project_instruction;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getProject_location() {
		return project_location;
	}
	public void setProject_location(String project_location) {
		this.project_location = project_location;
	}
	public short getFinish_or_not() {
		return finish_or_not;
	}
	public void setFinish_or_not(short finish_or_not) {
		this.finish_or_not = finish_or_not;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getClassification_algorithm() {
		return classification_algorithm;
	}
	public void setClassification_algorithm(String classification_algorithm) {
		this.classification_algorithm = classification_algorithm;
	}
	public boolean isStatistical_analysis() {
		return statistical_analysis;
	}
	public void setStatistical_analysis(boolean statistical_analysis) {
		this.statistical_analysis = statistical_analysis;
	}
	public String getWord_segmentation() {
		return word_segmentation;
	}
	public void setWord_segmentation(String word_segmentation) {
		this.word_segmentation = word_segmentation;
	}
	public String getClustering_algorithm() {
		return clustering_algorithm;
	}
	public void setClustering_algorithm(String clustering_algorithm) {
		this.clustering_algorithm = clustering_algorithm;
	}
	public String getSVM_value() {
		return SVM_value;
	}
	public void setSVM_value(String sVM_value) {
		SVM_value = sVM_value;
	}
	public String getKmeans_value() {
		return Kmeans_value;
	}
	public void setKmeans_value(String kmeans_value) {
		Kmeans_value = kmeans_value;
	}
}
