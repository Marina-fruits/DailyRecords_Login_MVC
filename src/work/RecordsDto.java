package work;

import java.sql.Timestamp;

/**----------------------------------------------------------------------*
 *■■■RecordsDtoクラス■■■
 *概要：DTO（「reflection」テーブル）
 *----------------------------------------------------------------------**/
public class RecordsDto {

	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private String    initiative ;                //Initiativeを持って取り組んだこと
	private String       accomplishment ;                 //Achievement（成果）
	private String       to_improve_work ;                 //仕事の改善点（things to improve in work）
	private String       to_improve_commu ;   //コミュの改善点（things to improve in commu）
	private Timestamp time ;                //更新時刻

	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------

	//getter/setter（対象フィールド：Initiativeを持って取り組んだこと）
	public String getInitiative() { return initiative; }
	public void setInitiative(String initiative) { this.initiative = initiative; }

	//getter/setter（対象フィールド：Achievement（成果））
	public String getAccomplishment() { return accomplishment; }
	public void setAccomplishment(String accomplishment) { this.accomplishment = accomplishment; }

	//getter/setter（対象フィールド：仕事の改善点（things to improve in work））
	public String getTo_improve_work() { return to_improve_work; }
	public void setTo_improve_work(String to_improve_work) { this.to_improve_work = to_improve_work; }

	//getter/setter（対象フィールド：コミュの改善点（things to improve in commu））
	public String getTo_improve_commu() { return to_improve_commu; }
	public void setTo_improve_commu(String to_improve_commu) { this.to_improve_commu = to_improve_commu; }

	//getter/setter（対象フィールド：time）
	public Timestamp getTime() { return time; }
	public void setTime(Timestamp time) { this.time = time; }

}
