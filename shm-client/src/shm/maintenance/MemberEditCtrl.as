package shm.maintenance {
	import mx.core.UIComponent;
	import mx.rpc.events.ResultEvent;
	
	import shm.common.UICtrlBase;

	public class MemberEditCtrl extends UICtrlBase {
		private var view:MemberEdit;

		public function MemberEditCtrl() {
			super();
		}

		protected override function doInitialize(component:UIComponent, id:String):void {
			view = MemberEdit(component);
		}

		protected override function doOnServiceSuccess(event:ResultEvent):void {
			// 一覧の更新
			view.listService.send();
		}

	}
}