package cn.vfunding.vfunding.biz.inviteCode.status;

/**
 * 
 * @author wang.zeyan
 * @date 2015年3月3日
 * @version 1.0
 */
public class StatusUtil {

	/**
	 * 
	 * <p>获得状态的二进制比较值 ，index从右至左</p>
	 * 
	 * 
	 * <pre>
	 * getStatusCompareValue(0) = 1
	 * getStatusCompareValue(1) = 2
	 * getStatusCompareValue(2) = 4
	 * getStatusCompareValue(3) = 8
	 * getStatusCompareValue(4) = 16
	 * </pre>
	 * 
	 * wang.zeyan 2015年3月3日
	 * 
	 * @param index
	 * @return
	 */
	public synchronized static int getStatusCompareValue(int index) {

		return 1 << (index - 1);
	}
	
	/**
	 * 
	 * <p>根据原始值 ，比较原始值的二进制，index从右至左</p>
	 * 
	 * 
	 * <pre>
	 * intCompare(15 , 3) = 4	意味当前index 3 的状态为1
	 * 15的二进制码: 				1111
	 * index 3的比较值4,4的二进制码    0100
	 * 得到二进制结果                                     0100
	 * 
	 * intCompare(8 , 3) = 0   	意味当前index 3 的状态为0
	 * 8的二进制码: 			    1000
	 * index 3的比较值4,4的二进制码    0100
	 * 得到二进制结果                                     0000
	 * </pre>
	 * 
	 * wang.zeyan 2015年3月3日
	 * @param srcValue
	 * @param index
	 * @return
	 */
	public synchronized static int intCompare(int srcValue , int index){
		
		return srcValue & getStatusCompareValue(index);
	}
	
	
	/**
	 * 
	 * <p>根据原始值 ，比较原始值的二进制，index从右至左</p>
	 * 
	 * 
	 * <pre>
	 * booleanCompare(15 , 3) = true	意味当前index 3 的状态为1
	 * 15的二进制码: 				    1111
	 * index 2的比较值4,4的二进制码              0100
	 * 得到二进制结果                                              0100
	 * 
	 * booleanCompare(8 , 3) = false   	意味当前index 3 的状态为0
	 * 8的二进制码: 			        1000
	 * index 2的比较值4,4的二进制码              0100
	 * 得到二进制结果                                              0000
	 * </pre>
	 * 
	 * wang.zeyan 2015年3月3日
	 * @param srcValue
	 * @param index
	 * @return
	 */
	public synchronized static boolean booleanCompare(int srcValue , int index){
		
		return intCompare(srcValue,index) == getStatusCompareValue(index);
	}
	
	/**
	 * <p> 根据原始值，index, status[要更新的状态(0,1)] 获得所需要的值 <p>
	 * 
	 * <pre>
	 * getStatusUpdateValue(8 , 3 ) = 12
	 *     解析:   8的二进制码为 : 1000 要更新从右到左的第3个状态为(0 or 1) 则应为 1100 =12
	 * getStatusUpdateValue(8 , 2 ) = 10
	 * getStatusUpdateValue(15, 4 ) = 7
	 *     解析:  15的二进制码为 : 1111 要更新从右到左的第4个状态为(0 or 1) 则应从 1111 变更为 0111
	 * getStatusUpdateValue(7 , 4 ) = 15
	 * </pre>
	 * 
	 * wang.zeyan 2015年3月3日
	 * @param srcValue
	 * @param index
	 * @return
	 */
	public synchronized static int getStatusUpdateValue(int srcValue , int index){
		
		return srcValue ^ getStatusCompareValue(index);
	}
	
}
