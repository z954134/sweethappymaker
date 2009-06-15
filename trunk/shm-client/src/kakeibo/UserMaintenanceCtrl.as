package kakeibo
{
	import kakeibo.entity.User;
	import kakeibo.events.RecordUpdateWindowEvent;
	import kakeibo.utils.UICtrlBase;
	
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.managers.PopUpManager;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	public class UserMaintenanceCtrl extends UICtrlBase
	{
		private var view:UserMaintenance;
		
		private var userService:RemoteObject = new RemoteObject("userService");
		 
		/** 初期化時のコールバック */ 
		protected override function doInitialize(ui:UIComponent, id:String):void
		{
			view = UserMaintenance(ui);
		}
		/** 生成完了時のコールバック */
		protected override function onCreationCompleted(event:FlexEvent):void
		{
			view.userGrid.addEventListener(ListEvent.CHANGE, onUserGridChanged);
			refresh();
		}
		
		/** グリッド変更時 */
		public function onUserGridChanged(event:ListEvent):void
		{
			checkStatus();
		}
		
		private function checkStatus():void
		{
			view.currentState =
				view.userGrid.selectedItem == null ? "Unselected" : "Selected";
		}
		
		public function onInsertButtonClicked():void
		{
			var window:UserEditWindow = createWindow("Insert");
		}
		
		public function onUpdateButtonClicked():void
		{
			var window:UserEditWindow = createWindow("Update");
			window.ctrl.user = view.userGrid.selectedItem as User;
		}
		
		public function onDeleteButtonClicked():void
		{
			var window:UserEditWindow = createWindow("Delete");
			window.ctrl.user = view.userGrid.selectedItem as User;
		}
		
		private function recordUpdateWindowCompleteHander(event:RecordUpdateWindowEvent):void {
			refresh();
		}
		
		public function refresh():void
		{
			view.reloadButton.enabled = false;
			var token:AsyncToken = view.userService.findAll();
			token.addResponder(new AsyncResponder(
			function (e:ResultEvent, token:Object):void
			{
				checkStatus();
				view.reloadButton.enabled = true;
			}
			,null));
		}
		
		private function createWindow(state:String):UserEditWindow
		{
			// ポップアップの生成
			var window:UserEditWindow = PopUpManager.createPopUp
					(view, UserEditWindow, true) as UserEditWindow;
			// 完了イベントをリスン
			window.addEventListener(RecordUpdateWindowEvent.UPDATE_COMPLETE,
				recordUpdateWindowCompleteHander);
			// 状態を設定
			window.currentState = state;
			// ポップアップ表示
			PopUpManager.centerPopUp(window);
			return window;
		}
	}
}