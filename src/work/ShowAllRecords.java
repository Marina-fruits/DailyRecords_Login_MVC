package work;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**----------------------------------------------------------------------*
 *■■■ShowAllRecordsクラス■■■
 *概要：サーブレット
 *詳細：「reflection」テーブルのデータを全件抽出して記録一覧画面を出力する。
 *----------------------------------------------------------------------**/
public class ShowAllRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowAllRecords() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");

		//セッションからユーザーデータを取得
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態によって表示画面を振り分ける
		if (userInfoOnSession != null) {
			//ログイン済：記録一覧画面を出力

			//出力用のストリームの取得
			PrintWriter out = response.getWriter();

			//「reflection」テーブルのデータを全件抽出
			List<RecordsDto> list  = new ArrayList<RecordsDto>();
//			ビジネスロジックをインスタンス化し、セレクトするメソッドを呼び出し、listで受け取る
			ShowAllRecordsBL logic = new ShowAllRecordsBL();
			list = logic.executeSelectRecords();

			//HTML文書（記録一覧画面）の出力
			out.println(        "<html>                                                      ");
			out.println(        "  <head>                                                    ");
			out.println(        "    <title>記録一覧</title>                                 ");
			out.println(        "  </head>                                                   ");
			out.println(        "  <body>                                                    ");
			out.println(        "    <h2>記録一覧</h2>                             ");
			out.println(        "    <table class=\"surbey_list\" border=1>                  ");
			out.println(        "      <tr bgcolor=\"#c0c0c0\">                              ");
			out.println(        "        <th>自主的に行ったこと</th>                                       ");
			out.println(        "        <th>高い成果を出したこと</th>                                       ");
			out.println(        "        <th>改善点（仕事の仕方）</th>                                       ");
			out.println(        "        <th>改善点（コミュニケーション）</th>                                     ");
			out.println(        "        <th>記録時間</th>                                   ");
			out.println(        "      </tr>                                                 ");

			for (int i = 0; i < list.size(); i++) {
				RecordsDto dto = list.get(i);
				out.println(    "      </tr>                                                 ");
				out.println(    "        <td>" + replaceEscapeChar(dto.getInitiative()) + "</td>");
				out.println(    "        <td>" + replaceEscapeChar(dto.getAccomplishment()) + "</td>");
				out.println(    "        <td>" + replaceEscapeChar(dto.getTo_improve_work()) + "</td>");
				out.println(    "        <td>" + replaceEscapeChar(dto.getTo_improve_commu()) + "</td>");
				out.println(    "        <td>" + dto.getTime()    + "</td>"                   );
				out.println(    "      </tr>                                                 ");
			}
			out.println(        "    </table>                                                ");
			out.println(        "    <br>                                                    ");
			out.println(        "    <a href=\"InputRecords\">記録画面に戻る</a>              ");
			out.println(        "  </body>                                                   ");
			out.println(        "</html>                                                     ");

		} else {
			//未ログイン：ログイン画面へ転送
			response.sendRedirect("Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**----------------------------------------------------------------------*
	 *■■■replaceEscapeCharクラス■■■
	 *概要：文字列データのエスケープを行う
	 *----------------------------------------------------------------------**/
	private String replaceEscapeChar(String inputText) {

		String charAfterEscape = inputText ; //エスケープ後の文字列データ

		// 「&」を変換
		charAfterEscape = inputText.replace("&", "&amp;");
		// 「<」を変換
		charAfterEscape = inputText.replace("<", "&lt;");
		// 「>」を変換
		charAfterEscape = inputText.replace(">", "&gt;");
		// 「"」を変換
		charAfterEscape = inputText.replace("\"", "&quot;");
		// 「'」を変換
		charAfterEscape = inputText.replace("'", "&#039;");

		return charAfterEscape;
	}
}
