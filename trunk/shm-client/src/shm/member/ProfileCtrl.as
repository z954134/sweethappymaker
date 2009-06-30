package shm.member {
	import flash.events.MouseEvent;
	
	import mx.controls.Alert;
	import mx.core.Application;
	import mx.core.UIComponent;
	import mx.events.CloseEvent;
	import mx.events.FlexEvent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.ResultEvent;
	import mx.validators.Validator;
	
	import shm.common.UICtrlBase;

	public class ProfileCtrl extends UICtrlBase {
		private var view:ProfileEditWindow;

		protected override function doInitialize(component:UIComponent, id:String):void {
			view = ProfileEditWindow(component);
		}

		protected override function onCreationCompleted(event:FlexEvent):void {
			view.selectService.send();
		}

		public function onUpdateButtonClicked(event:MouseEvent):void {
			var validationResults:Array = Validator.validateAll(view.validators);
			if (validationResults.length > 0) {
				return;
			}
			view.updateService.send();
		}

		public function onSelectCompleted(event:ResultEvent):void {
			var m:Object = event.result.member;
			view.key.text = m.key;
			view.memberIdText.text = m.memberId;
			view.passwordText.text = m.password;
			view.emailText.text = m.email;
		}

		public function onCancelButtonClick(event:MouseEvent):void {
			removePopUp();
		}

		public function onCloseButtonClicked(event:CloseEvent):void {
			removePopUp();
		}

		protected override function doOnServiceSuccess(event:ResultEvent):void {
			Alert.show("更新しました");
			removePopUp();
		}

		private function removePopUp():void {
			PopUpManager.removePopUp(view);
		}
	}
}