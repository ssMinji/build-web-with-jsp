import java.sql.*;



/*
 * �ǽ� ����
 * EMP ���̺�� ���� �۾��غ��� ���� 
 * 
 * �����غ���) 
 * sqlplus�� EMP ���̺� ��Ű��, ������ �ѹ� ���� ���� 
 *  
 * ����)
 * emp ���̺� ������ 
 * insert 
 * select
 * update
 * delete 
 * �غ���
 * */
public class dbtest2 {

	public static void main(String[] args) {
		Connection conn = null;
		
		// DB url ����
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // ip, ��Ʈ, ����(SID) ���� 
        String id = "scott"; 
        String pw = "tiger";
        
        
        Statement stmt = null; 			// ���̺� ���� SQL����
        PreparedStatement pstmt = null; // �Ű������� �̿��� SQL ����
        ResultSet rs = null; 			// SELECT ��� ���� ResultSet ����
        String sql = "";				// SQL�� ���� ����

        // SQL ���� �޼ҵ� ���� SQLException ���ܸ� ó���ؾ� ��
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(url, id, pw);
            System.out.println(conn);    

            stmt = conn.createStatement();

            sql = "INSERT INTO EMP VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, 1234);
            pstmt.setString(2, "KIM");
            pstmt.setString(3, "TEACHER");
            pstmt.setInt(4, 7568);
            pstmt.setString(5, "79/07/10");
            pstmt.setInt(6, 2500);
            pstmt.setInt(7, 600);
            pstmt.setInt(8, 20);

            int insert = pstmt.executeUpdate();
            System.out.println(insert + " ���� ���� �ԷµǾ����ϴ�.");  

            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM EMP ORDER BY EMPNO");

            while (rs.next()) {
            	System.out.println("---------------------------------------");
                System.out.println("�����ȣ: " + rs.getInt("EMPNO"));
                System.out.println("�̸�: " + rs.getString("ENAME"));
                System.out.println("����: " + rs.getString("JOB"));
                System.out.println("������: " + rs.getInt(4));
                System.out.println("�Ի���: " + rs.getString(5));
                System.out.println("�޿�: " + rs.getInt(6));
                System.out.println("Ŀ�̼�: " + rs.getInt(7));
                System.out.println("�μ���ȣ: " + rs.getInt(8));
                System.out.println("---------------------------------------");
                
            }
            
            
            
            // update ���� 
            sql = "UPDATE EMP SET ENAME = ? WHERE ENAME = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "KANG");
            pstmt.setString(2, "KIM");
            
            pstmt.executeUpdate();
            
            // delete ���� 
            sql = "DELETE FROM EMP WHERE EMPNO = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1234);
            
            pstmt.executeUpdate();
            

        } catch (Exception e) {
            System.out.println(e);

          // ���ܿ� ������� �޸𸮿��� �����ϱ� ���� finally���� �ۼ�
        } finally {
            // close() ���� ���� ó���� �ʿ���
            try {
            	// ���� ���� 
                // �޸� ����
                rs.close();
                pstmt.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
	}

}
