import java.sql.*;

public class dbtest {

	public static void main(String[] args) {
		Connection conn = null;
		
		// DB url ����
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // ip, ��Ʈ, ����(SID) ���� 
        String id = "scott"; 
        String pw = "tiger";
        
        
        // SQL ���� ��ü ����
        // try~catch�� ���ο� �����ϸ� ������ �ν��� ���ϴ� ��찡 �߻�
        Statement stmt = null; 			// ���̺� ���� SQL����
        PreparedStatement pstmt = null; // �Ű������� �̿��� SQL ����
        ResultSet rs = null; 			// SELECT ��� ���� ResultSet ����
        String sql = "";				// SQL�� ���� ����

        // SQL ���� �޼ҵ� ���� SQLException ���ܸ� ó���ؾ� ��
        try {
            // JDBC ����̹��� �޸𸮿� �ø�
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // ������ DB ������ Connection ��ü ����
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println(conn);    // oracle.jdbc.driver.T4CConnection@61e717c2

            // Statement ��ü ����
            // sql�� �����ͺ��̽��� ���� ��û�ϱ� ���� 
            stmt = conn.createStatement();

            // ���̺� ���� SQL���� �ۼ�
            sql = "CREATE TABLE PEOPLE(NAME VARCHAR2(20), AGE NUMBER)";

            // sql���� ���� 
            stmt.executeUpdate(sql);

            // INSERT��, ���̺� ������ �Է�
            sql = "INSERT INTO PEOPLE VALUES(?, ?)";

            // SQL������ �Ű������� PreparedStatement ��ü ����
            pstmt = conn.prepareStatement(sql);

            // ù ��° �Ű�����(?)�� "ȫ�浿" ����
            pstmt.setString(1, "��ö��");
            // �� ��° ?�� 34 ����
            pstmt.setInt(2, 23);

            // INSERT�� ���� �� ��ȯ �� ����
            int insert = pstmt.executeUpdate();

            // ��� Ȯ��
            System.out.println(insert + " ���� ���� �ԷµǾ����ϴ�.");  

            // SELECT��, ���̺� �� ������ ��ȸ
            stmt = conn.createStatement();

            // Query ��ȯ�� ResultSet ��ü ��ȯ
            rs = stmt.executeQuery("SELECT * FROM PEOPLE");

            // ������ �����͸� ���� ���� ����
            String name; 	// �̸�
            int age; 		// ����

            // ResultSet�� ���� �ο찡 �ִ� ���� ��� ������ ��ȸ
            System.out.println("---------------------------------------");
            while (rs.next()) {
                // name�� "name" �÷� ������ ����
                name = rs.getString("name");

                // age�� "age" �÷� ������ ����
                age = rs.getInt("age");

                // ���
                System.out.println("�̸�: " + name + ", ����: " + age);
                // �̸�: ȫ�浿, ����: 34
            }
            System.out.println("---------------------------------------");
            
            
            // update ���� 
            sql = "UPDATE PEOPLE SET AGE = ? WHERE NAME = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 32);
            pstmt.setString(2, "��ö��");
            
            pstmt.executeUpdate();
            
            // delete ���� 
            sql = "DELETE FROM PEOPLE WHERE AGE = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 21);
            
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
