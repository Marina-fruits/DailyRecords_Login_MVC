package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RecordsDto;
import model.SaveRecordsBL;
import model.UserInfoDto;

/**----------------------------------------------------------------------*
 *■■■SaveRecordsクラス■■■
 *概要：サーブレット
 *詳細：リクエスト（毎日の記録データ）を「reflection」テーブルに登録し、画面遷移する。
 *　　　＜遷移先＞登録成功：記録完了画面（finish.html）／登録失敗：エラー画面（error.html）
 *----------------------------------------------------------------------**/
public class SaveRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveRecords() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");                  //文字コードをUTF-8で設定

		//セッションからユーザーデータを取得
		HttpSession session           = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto)session.getAttribute("LOGIN_INFO");

		//ログイン状態によって表示画面を振り分ける
		if (userInfoOnSession != null) {
			//ログイン済：登録処理＆結果画面への遷移を実施
			boolean succesFlg = true;  //成功フラグ（true:成功/false:失敗）

				//リクエストパラメータを取得
				String initiative           = (request.getParameter("INITIATIVE"));                                //リクエストパラメータ（INITIATIVE）
				String accomplishment           = request.getParameter("ACCOMPLISHMENT");                        //リクエストパラメータ（ACCOMPLISHMENT）
				String to_improve_work           = request.getParameter("TO_IMPROVE_WORK");                      //リクエストパラメータ（TO_IMPROVE_WORK）
				String to_improve_commu           = request.getParameter("TO_IMPROVE_COMMU");                    //リクエストパラメータ（TO_IMPROVE_COMMU）

				//毎日の記録データ（RecordsDto型）の作成（今回リクエストパラメータで受け取った値を変数に入れていく）
				RecordsDto dto = new RecordsDto();
				dto.setInitiative( initiative );
				dto.setAccomplishment( accomplishment );
				dto.setTo_improve_work( to_improve_work );
				dto.setTo_improve_commu( to_improve_commu );
				dto.setTime( new Timestamp(System.currentTimeMillis()) );   //現在時刻を更新時刻として設定

				//毎日の記録データをDBに登録
				SaveRecordsBL logic = new SaveRecordsBL();
				succesFlg          = logic.executeInsertRecords(dto);  //成功フラグ（true:成功/false:失敗）

			//成功/失敗に応じて表示させる画面を振り分ける
			if (succesFlg) {

				//成功した場合、記録完了画面（finish.html）を表示する
				response.sendRedirect("htmls/finish.html");

			} else {

				//失敗した場合、エラー画面（error.html）を表示する
				response.sendRedirect("htmls/error.html");

			}

		} else {
			//未ログイン：ログイン画面へ転送
			response.sendRedirect("Login");
		}
	}
}
