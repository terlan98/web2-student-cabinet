package model;

public class Course
{
	private String name;
	private int id;
	private boolean isEnrolled = false;
	
	public Course(String name, int id)
	{
		this.name = name;
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public boolean isEnrolled()
	{
		return isEnrolled;
	}
	
	public void setEnrolled(boolean enrolled)
	{
		isEnrolled = enrolled;
	}
	
	@Override
	public String toString()
	{
		return "Course{" +
				"name='" + name + '\'' +
				", id=" + id +
				'}';
	}
}
