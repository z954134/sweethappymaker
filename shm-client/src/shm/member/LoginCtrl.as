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

	public class LoginCtrl extends UICtrlBase {
		private var view:LoginWindow;

		public function LoginCtrl() {
			super();
		}

		protected override function doInitialize(component:UIComponent, id:String):void {
			view = LoginWindow(component);
		}
		
		protected override function onCreationCompleted(event:FlexEvent):void {
			view.loginUrlService.send();
		}
		
		public function onLoginUrlSet(event:ResultEvent):void {
			var result:Object = event.result;
			var loginUrl:String = String(result.loginUrl);
			if (!loginUrl) {
				
			}
			view.loginService.url = loginUrl;

		}

		public function onLoginButtonClicked(event:MouseEvent):void {
			var validationResults:Array = Validator.validateAll(view.validators);
			if (validationResults.length > 0) {
				return;
			}
			view.loginService.send();
		}

		public function onCancelButtonClick(event:MouseEvent):void {
			removePopUp();
		}

		public function onCloseButtonClicked(event:CloseEvent):void {
			removePopUp();
		}


		protected override function doOnServiceSuccess(event:ResultEvent):void {
			var loginEvent:LoginEvent = new LoginEvent(LoginEvent.LOGIN_COMPLETE);
			loginEvent.success = true;
			loginEvent.memberId = view.memberIdField.text;
			view.dispatchEvent(loginEvent);
			Application.application.dispatchEvent(loginEvent);
			
			removePopUp();
			Alert.show("ログインしました", "ログイン");
		}

		private function removePopUp():void {
			PopUpManager.removePopUp(view);
		}
	}
}