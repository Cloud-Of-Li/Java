package work;
/**
 * ����
 *   ����Ϊ1��2��3...n��n����Χ��һȦ��Լ�����Ϊk��1<=k<=n�����˴�1��ʼ������
 * ����m���Ǹ��˳��У�������һλ�ִ�1��ʼ����������m���Ǹ��˳��У��������ƣ�֪�������˳���Ϊֹ
 * �ɴ˲���һ�����ӱ�����С�
 * 
 * 
 * ��ʾ��
 *   ���鷽ʽ����һ���ض�������������е��ˣ�����ȡ��ķ�ʽ�ҳ����е���������ı�Ų�����ֵ����Ϊ0����ɾ����Ȼ���ж���һ�����������ֵΪ��Ϊ0��
 * ��Ϊ0��������һ�����ҳ���һ����ʼ��ţ�ͨ������Ķ�Ӧ��ϵ���ҳ����б�š�
 *   ����ʽ����һ������ͷ�ڵ��ѭ���б�������Josephu���⣬�ȹ���һ����n�ڵ�ĵ�ѭ������Ȼ����k�ڵ㿪ʼ��1��ʼ����
 * �Ƶ�mʱ����Ӧ�ڵ��������ɾ����Ȼ���ٴӱ�ɾ���ڵ����һ������ִ�1��ʼ������ֱ�����һ������������ɾ��
 * �㷨������
 */
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
	private int total;//total��������n
	private int numtotal;
	private int first;//first����k
	private int num;//num����m
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
			createPeople();//�����鰴��ʼ˳���źá�
		int j=0;
		//������ʼλ֮��ı�ţ�������K
		for(int i=1;i<=total-first+1;i++){
			if(people[first+i-2]==0)
				continue;
			j++;
			people[first+(i-2)]=j;//j��¼���
		}		
		//������ʼλ֮ǰ�ı�ţ�������ʼ��
		for(int i=0;i<first-1;i++){
			if(people[i]==0)
				continue;
			j++;
			people[i]=j;
		}
	}	
	public int chulieNum(){
		int serial=1;
		//����ȡ�෽ʽ�������ҳ���
		for(int i=0;i<total;i++){
			if(people[i]==((num%numtotal==0)?numtotal:(num%numtotal))){
				serial=i+1;
				break;
			}
		}
		return serial;
	}
	public void firstNum(){
		//�ҵ���ʼ�ĸ��˵ı��
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
		people[chulieNum()-1]=0;//�����б���˵�ֵ����Ϊ0
		numtotal--;
		setPeople();
		
	}
	public void output(){
		int finallTotal=total;
//		int i=0;
//		System.out.print("�����������Ϊ��   ");
//		do{
//			i++;
//			//System.out.println("��"+i+"�����е��˱��Ϊ��"+chulieNum());
//			System.out.print(chulieNum()+"��");
//			xunhuan();
//
//		}
//		while(i<finallTotal);
		System.out.print("�������е��˱��Ϊ��    ");
		for(int i=0;i<finallTotal;i++){		
			if(i%20==0)
				System.out.println("\t\t\t");
			System.out.printf("%5d",chulieNum());
			xunhuan();
		}
	}
}