package top.cellargalaxy.learn. reflect;

/**
 * Created by cellargalaxy on 17-10-21.
 */
public class People implements HelloSay{
	
	static {
		System.out.println("执行了People的静态块");
	}
	
	private String name;
	private int age;
	
	public People() {
	}
	
	public People(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "People{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
	
	public void sayHello(String name, int age) {
		System.out.println("hello "+name+",you are "+age+" years.");
	}
	
	public void sayGoodBey(String name) {
		System.out.println("goodbey "+name);
	}
}
