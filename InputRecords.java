package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserInfoDto;
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
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/input_records.jsp");
			dispatch.forward(request, response);
		} else {
			//未ログイン：ログイン画面へ転送
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatch.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
