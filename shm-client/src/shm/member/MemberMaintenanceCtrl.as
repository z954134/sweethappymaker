package shm.member {
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.managers.PopUpManager;

	import shm.common.UICtrlBase;

	public class MemberMaintenanceCtrl extends UICtrlBase {
		private var view:MemberMaintenance;

		/** 初期化時のコールバック */
		protected override function doInitialize(ui:UIComponent, id:String):void {
			view = MemberMaintenance(ui);
		}

		/** 生成完了時のコールバック */
		protected override function onCreationCompleted(event:FlexEvent):void {
			view.memberGrid.addEventListener(ListEvent.CHANGE, onMemberGridChanged);
			refresh();
		}

		/** グリッド変更時 */
		public function onMemberGridChanged(event:ListEvent):void {
			checkStatus();
		}

		private function checkStatus():void {
			view.currentState = view.memberGrid.selectedItem == null ? "Unselected" :
				"Selected";
		}

		public function onInsertButtonClicked():void {
			var window:MemberEditWindow = createWindow("Insert");
		}

		public function onUpdateButtonClicked():void {
			var window:MemberEditWindow = createWindow("Update");
			window.ctrl.member = view.memberGrid.selectedItem as Member;
		}

		public function onDeleteButtonClicked():void {
			var window:MemberEditWindow = createWindow("Delete");
			window.ctrl.member = view.memberGrid.selectedItem as Member;
		}

		private function recordUpdateWindowCompleteHander(event:RecordUpdateWindowEvent):void {
			refresh();
		}

		public function refresh():void {
//			view.reloadButton.enabled = false;
//			var token:AsyncToken = view.userService.findAll();
//			token.addResponder(new AsyncResponder(function(e:ResultEvent, token:Object):void
//				{
//					checkStatus();
//					view.reloadButton.enabled = true;
//				}, null));
		}

		private function createWindow(state:String):MemberEditWindow {
			// ポップアップの生成
			var window:MemberEditWindow = PopUpManager.createPopUp(view, MemberEditWindow,
				true) as MemberEditWindow;
			// 完了イベントをリスン
			window.addEventListener(RecordUpdateWindowEvent.UPDATE_COMPLETE, recordUpdateWindowCompleteHander);
			// 状態を設定
			window.currentState = state;
			// ポップアップ表示
			PopUpManager.centerPopUp(window);
			return window;
		}
	}
}