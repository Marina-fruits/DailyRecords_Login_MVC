package work;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**----------------------------------------------------------------------*
 *■■■InputRecordsクラス■■■
 *概要：サーブレット
 *詳細：HTML文書（記録入力画面）を出力する。
 *----------------------------------------------------------------------**/
public class InputRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InputRecords() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");  //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");                  //文字コードをUTF-8で設定

		//セッションからユーザーデータを取得
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態によって表示画面を振り分ける
		if (userInfoOnSession != null) {
			//ログイン済：記録入力画面を出力

			//出力用のストリームの取得
			PrintWriter out = response.getWriter();

			//今日の日付の取得
			SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
	        Calendar calendar = Calendar.getInstance();
	        Date dateObj = calendar.getTime();
	        String formattedDate = dtf.format(dateObj);

			//HTML文書（記録入力画面）の出力
			out.println("<html>                                                                                                    ");
			out.println("<head>                                                                                                    ");
			out.println("  <title>記録入力</title>                                                                                 ");
			out.println("</head>                                                                                                   ");
			out.println("<body>                                                                                                    ");
			out.println("  <h2>毎日の改善</h2>                                                         ");
			out.println("  <form action=\"SaveRecords\" method=\"post\">                                                            ");
			out.println("    <p>日付：" + formattedDate                                                                                  );
			out.println("      <input type=\"hidden\" name=\"DATE\"  value=\""+ formattedDate +"\"> "                                    );
			out.println("    </p>                                                                                                  ");
			out.println("    <p>自主的に行ったこと<br>                                                     ");
			out.println("      <textarea name=\"INITIATIVE\" rows=\"4\" cols=\"50\" id=\"INITIATIVE\" maxlength=\"200\"></textarea>");
			out.println("    </p>                                                                                                  ");
			out.println("    <p>良かったこと<br>                                                     ");
			out.println("      <textarea name=\"ACCOMPLISHMENT\" rows=\"4\" cols=\"50\" id=\"ACCOMPLISHMENT\"></textarea> ");
			out.println("    </p>                                                                                                  ");
			out.println("    <p>改善点（仕事の仕方）<br>                                                     ");
			out.println("      <textarea name=\"TO_IMPROVE_WORK\" rows=\"4\" cols=\"50\"  id=\"TO_IMPROVE_WORK\"></textarea> ");
			out.println("    </p>                                                                                                  ");
			out.println("    <p>改善点（コミュニケーション）<br>                                                     ");
			out.println("      <textarea name=\"TO_IMPROVE_COMMU\" rows=\"4\" cols=\"50\" id=\"TO_IMPROVE_COMMU\"></textarea> ");
			out.println("    </p>                                                                                                  ");
			out.println("    <input type=\"submit\" value=\"記録する\" id=\"ID_SUBMIT\">                         ");
			out.println("  </form>                                                                                                 ");
			out.println("  <script type=\"text/javascript\" src=\"js/input_records.js\"></script>                                   ");
			out.println("  <br>                                                                                                    ");
			out.println("  <a href=\"ShowAllRecords\">記録一覧画面へ</a>                                                            ");
			out.println("  <br>                                                                                                    ");
			out.println("  <a href=\"ExecuteLogout\">ログアウトする</a>                                                            ");
			out.println("</body>                                                                                                   ");
			out.println("</html>                                                                                                   ");

		} else {
			//未ログイン：ログイン画面へ転送
			response.sendRedirect("Login");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
