package work;
/**
 * 需求：
 *   设编号为1，2，3...n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，
 * 数到m的那个人出列，他的下一位又从1开始报数，数到m的那个人出列，依次类推，知道所有人出列为止
 * 由此产生一个出队编号序列。
 * 
 * 
 * 提示：
 *   数组方式：用一个特定的数组代表所有的人，采用取余的方式找出出列的人所代表的编号并将其值设置为0代表删除，然后判断下一数组数代表的值为不为0，
 * 若为0，跳过，一步步找出下一个起始编号，通过数组的对应关系，找出所有编号。
 *   链表方式：用一个不带头节点的循环列表来处理Josephu问题，先构成一个有n节点的单循环链表，然后由k节点开始从1开始计数
 * 计到m时，对应节点从链表中删除，然后再从被删除节点的下一个结点又从1开始计数，直到最后一个结点从链表中删除
 * 算法结束。
 */
//今天是2017/5/5测试Github
public class JosephuForArray{		
	public static void main(String []args){
		CycleLink cl=new CycleLink();
		cl.setTotal(5);
		cl.setFirst(3);
		cl.setNum(6);
		cl.setPeople();
		cl.output();
	}
}
class CycleLink{
	private int total;//total代表总数n
	private int numtotal;
	private int first;//first代表k
	private int num;//num代表m
	private int []people;
	public void setTotal(int total){
		this.total=total;
		numtotal=total;
	}
	public void setFirst(int first){
		this.first=first;
	}
	public void setNum(int num){
		this.num=num;
	}
	public int[] createPeople(){
		people=new int [total];
		for(int i=0;i<total;i++){
			people[i]=i+1;
		}
		return people;
	}
	public void setPeople(){
		if(people==null)
			createPeople();//将数组按初始顺序排好。
		int j=0;
		//设置起始位之后的编号，不包括K
		for(int i=1;i<=total-first+1;i++){
			if(people[first+i-2]==0)
				continue;
			j++;
			people[first+(i-2)]=j;//j记录编号
		}		
		//设置起始位之前的编号，包括起始的
		for(int i=0;i<first-1;i++){
			if(people[i]==0)
				continue;
			j++;
			people[i]=j;
		}
	}	
	public int chulieNum(){
		int serial=1;
		//采用取余方式将该数找出来
		for(int i=0;i<total;i++){
			if(people[i]==((num%numtotal==0)?numtotal:(num%numtotal))){
				serial=i+1;
				break;
			}
		}
		return serial;
	}
	public void firstNum(){
		//找到起始的该人的编号
		int i=0;
		if(chulieNum()==total){			
			for(i=0;i<total;i++){
				if(people[i]!=0){
					setFirst(i+1);
					return;
				}
			}			
		}
		else{
			for(i=chulieNum();i<total;i++){
				if(people[i]!=0){
					setFirst(i+1);
					return;
				}			
			}
			for(i=0;i<chulieNum();i++){
				if(people[i]!=0){
					setFirst(i+1);
					return;
				}
			}
		}
	}
	public void xunhuan(){
		
//		setTotal(total-1);
		firstNum();
		people[chulieNum()-1]=0;//将出列编号人的值设置为0
		numtotal--;
		setPeople();
		
	}
	public void output(){
		int finallTotal=total;
//		int i=0;
//		System.out.print("各个出列序号为：   ");
//		do{
//			i++;
//			//System.out.println("第"+i+"个出列的人编号为："+chulieNum());
//			System.out.print(chulieNum()+"、");
//			xunhuan();
//
//		}
//		while(i<finallTotal);
		System.out.print("各个出列的人编号为：    ");
		for(int i=0;i<finallTotal;i++){		
			if(i%20==0)
				System.out.println("\t\t\t");
			System.out.printf("%5d",chulieNum());
			xunhuan();
		}
	}
}
