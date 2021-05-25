import java.sql.*;

public class dbtest {

	public static void main(String[] args) {
		Connection conn = null;
		
		// DB url 변수
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // ip, 포트, 서비스(SID) 정보 
        String id = "scott"; 
        String pw = "tiger";
        
        
        // SQL 문장 객체 변수
        // try~catch문 내부에 선언하면 변수로 인식을 못하는 경우가 발생
        Statement stmt = null; 			// 테이블 생성 SQL문장
        PreparedStatement pstmt = null; // 매개변수를 이용한 SQL 문장
        ResultSet rs = null; 			// SELECT 결과 저장 ResultSet 변수
        String sql = "";				// SQL문 저장 변수

        // SQL 관련 메소드 사용시 SQLException 예외를 처리해야 함
        try {
            // JDBC 드라이버를 메모리에 올림
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 접속할 DB 정보로 Connection 객체 생성
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println(conn);    // oracle.jdbc.driver.T4CConnection@61e717c2

            // Statement 객체 생성
            // sql문 데이터베이스로 실행 요청하기 위함 
            stmt = conn.createStatement();

            // 테이블 생성 SQL문장 작성
            sql = "CREATE TABLE PEOPLE(NAME VARCHAR2(20), AGE NUMBER)";

            // sql문장 실행 
            stmt.executeUpdate(sql);

            // INSERT문, 테이블에 데이터 입력
            sql = "INSERT INTO PEOPLE VALUES(?, ?)";

            // SQL문장을 매개변수로 PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);

            // 첫 번째 매개변수(?)에 "홍길동" 전달
            pstmt.setString(1, "김철수");
            // 두 번째 ?에 34 전달
            pstmt.setInt(2, 23);

            // INSERT문 실행 및 반환 값 저장
            int insert = pstmt.executeUpdate();

            // 결과 확인
            System.out.println(insert + " 개의 행이 입력되었습니다.");  

            // SELECT문, 테이블 내 데이터 조회
            stmt = conn.createStatement();

            // Query 반환값 ResultSet 객체 반환
            rs = stmt.executeQuery("SELECT * FROM PEOPLE");

            // 각각의 데이터를 담을 변수 선언
            String name; 	// 이름
            int age; 		// 나이

            // ResultSet의 다음 로우가 있는 동안 계속 데이터 조회
            System.out.println("---------------------------------------");
            while (rs.next()) {
                // name에 "name" 컬럼 데이터 저장
                name = rs.getString("name");

                // age에 "age" 컬럼 데이터 저장
                age = rs.getInt("age");

                // 출력
                System.out.println("이름: " + name + ", 나이: " + age);
                // 이름: 홍길동, 나이: 34
            }
            System.out.println("---------------------------------------");
            
            
            // update 쿼리 
            sql = "UPDATE PEOPLE SET AGE = ? WHERE NAME = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 32);
            pstmt.setString(2, "김철수");
            
            pstmt.executeUpdate();
            
            // delete 쿼리 
            sql = "DELETE FROM PEOPLE WHERE AGE = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 21);
            
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
