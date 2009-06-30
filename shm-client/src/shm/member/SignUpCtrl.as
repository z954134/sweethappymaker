package shm.member {
	import flash.events.MouseEvent;
	
	import mx.controls.Alert;
	import mx.core.UIComponent;
	import mx.events.CloseEvent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.ResultEvent;
	import mx.validators.Validator;
	
	import shm.common.UICtrlBase;

	public class SignUpCtrl extends UICtrlBase {
		private var view:SignUpWindow;

		public function SignUpCtrl() {
			super();
		}

		protected override function doInitialize(component:UIComponent, id:String):void {
			view = SignUpWindow(component);
		}

		public function onRegisterButtonClicked(event:MouseEvent):void {
			var validationResults:Array = Validator.validateAll(view.validators);
			if (validationResults.length > 0) {
				return;
			}
			view.serv.send();
		}

		public function onCancelButtonClick(event:MouseEvent):void {
			removePopUp();
		}

		public function onCloseButtonClicked(event:CloseEvent):void {
			removePopUp();
		}

		protected override function doOnServiceSuccess(event:ResultEvent):void {
			Alert.show("登録しました");
			removePopUp();
		}

		private function removePopUp():void {
			PopUpManager.removePopUp(view);
		}
	}
}