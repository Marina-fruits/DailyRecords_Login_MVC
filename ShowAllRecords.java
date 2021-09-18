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
//			Reflectionテーブルのデータを全件抽出（この文は不要だと思っている。講義動画16の5:04 45-51行目）
			//ログイン済：記録一覧画面を出力
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
