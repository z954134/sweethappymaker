package shm.member {
	import flash.events.MouseEvent;

	import mx.controls.Alert;
	import mx.core.UIComponent;
	import mx.events.CloseEvent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.ResultEvent;
	import mx.validators.Validator;

	import shm.member.LoginEvent;
	import shm.common.UICtrlBase;

	public class LoginCtrl extends UICtrlBase {
		private var view:LoginWindow;

		public function LoginCtrl() {
			super();
		}

		protected override function doInitialize(component:UIComponent, id:String):void {
			view = LoginWindow(component);
		}

		public function onLoginButtonClicked(event:MouseEvent):void {
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



		public function onServiceCompleted(event:ResultEvent):void {
			var loginEvent:LoginEvent = new LoginEvent(LoginEvent.LOGIN_COMPLETE);

			var r:Object = event.result;
			var authResult:String = r.authentication.result;
			switch (authResult) {
				case "success":
					loginEvent.success = true;
					loginEvent.memberId = view.memberIdField.text;
					view.dispatchEvent(loginEvent);
					removePopUp();
					break;
				case "failure":
					Alert.show("メンバーID、パスワードを確認してください", "ログイン失敗");
					break;
				default:
					throw new Error("変な状態");
			}
		}

		private function removePopUp():void {
			PopUpManager.removePopUp(view);
		}
	}
}