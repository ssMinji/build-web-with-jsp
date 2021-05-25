import java.sql.*;



/*
 * 실습 문제
 * EMP 테이블로 직접 작업해보게 유도 
 * 
 * 같이해보기) 
 * sqlplus로 EMP 테이블 스키마, 데이터 한번 보고 시작 
 *  
 * 문제)
 * emp 테이블에 데이터 
 * insert 
 * select
 * update
 * delete 
 * 해보기
 * */
public class dbtest2 {

	public static void main(String[] args) {
		Connection conn = null;
		
		// DB url 변수
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // ip, 포트, 서비스(SID) 정보 
        String id = "scott"; 
        String pw = "tiger";
        
        
        Statement stmt = null; 			// 테이블 생성 SQL문장
        PreparedStatement pstmt = null; // 매개변수를 이용한 SQL 문장
        ResultSet rs = null; 			// SELECT 결과 저장 ResultSet 변수
        String sql = "";				// SQL문 저장 변수

        // SQL 관련 메소드 사용시 SQLException 예외를 처리해야 함
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
            System.out.println(insert + " 개의 행이 입력되었습니다.");  

            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM EMP ORDER BY EMPNO");

            while (rs.next()) {
            	System.out.println("---------------------------------------");
                System.out.println("사원번호: " + rs.getInt("EMPNO"));
                System.out.println("이름: " + rs.getString("ENAME"));
                System.out.println("업무: " + rs.getString("JOB"));
                System.out.println("상관사번: " + rs.getInt(4));
                System.out.println("입사일: " + rs.getString(5));
                System.out.println("급여: " + rs.getInt(6));
                System.out.println("커미션: " + rs.getInt(7));
                System.out.println("부서번호: " + rs.getInt(8));
                System.out.println("---------------------------------------");
                
            }
            
            
            
            // update 쿼리 
            sql = "UPDATE EMP SET ENAME = ? WHERE ENAME = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "KANG");
            pstmt.setString(2, "KIM");
            
            pstmt.executeUpdate();
            
            // delete 쿼리 
            sql = "DELETE FROM EMP WHERE EMPNO = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1234);
            
            pstmt.executeUpdate();
            

        } catch (Exception e) {
            System.out.println(e);

          // 예외와 상관없이 메모리에서 해제하기 위해 finally문에 작성
        } finally {
            // close() 역시 예외 처리가 필요함
            try {
            	// 연결 종료 
                // 메모리 해제
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
