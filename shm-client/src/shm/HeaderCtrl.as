package shm {
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import mx.controls.Alert;
	import mx.core.Application;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.ResultEvent;
	
	import shm.common.UICtrlBase;
	import shm.member.LoginEvent;
	import shm.member.LoginWindow;
	import shm.member.ProfileEditWindow;
	import shm.member.SignUpWindow;

	public class HeaderCtrl extends UICtrlBase {

		private var header:Header;
		
		private var loginUrl:String;

		private var loginWindow:LoginWindow;

		private var signupWindow:SignUpWindow;

		private var profileWindow:ProfileEditWindow;

		protected override function doInitialize(component:UIComponent, id:String):void {
			header = Header(component);
		}


		protected override function onCreationCompleted(event:FlexEvent):void {
			header.logoutService.send();
			header.currentState = "notLoggedIn";
			header.loginUrlService.send();
		}

		public function onLoginOutButttonClicked(event:MouseEvent):void {
			switch (header.currentState) {
				case "loggedIn":
					logout();
					break;
				case "notLoggedIn":
					login();
					break;
				default:
					break;
			}
		}

		public function onSignUpOrEditButtonClicked(event:MouseEvent):void {
			switch (header.currentState) {
				case "loggedIn":
					profileEdit();
					break;
				case "notLoggedIn":
					signUp();
					break;
				default:
					break;
			}
		}

		public function test(event:Event):void {
			header.logintestService.send();
		}
		
		public function dummy(event:Event):void {
			
		}
		private function login():void {

			var request:URLRequest = new URLRequest(loginUrl);
			navigateToURL(request, "_self");
//			loginWindow = PopUpManager.createPopUp(header, LoginWindow, true) as
//				LoginWindow;
//			loginWindow.addEventListener(LoginEvent.LOGIN_COMPLETE, onLoginComplete);
//			PopUpManager.centerPopUp(loginWindow);
		}


		public function onLoginUrlSet(event:ResultEvent):void {
			var result:Object = event.result;
			var loginUrl:String = String(result.loginUrl);
			if (!loginUrl) {
				
			}
			this.loginUrl = loginUrl;

		}

		private function logout():void {
			header.logoutService.send();
			header.memberIdText.text = "guest";
			header.currentState = "notLoggedIn";
			Alert.show("ログアウトしました。", "ログアウト");
			Application.application.dispatchEvent(new LoginEvent(LoginEvent.LOGIN_COMPLETE));
		}

		private function signUp():void {
			signupWindow = PopUpManager.createPopUp(header, SignUpWindow, true) as
				SignUpWindow;
			PopUpManager.centerPopUp(signupWindow);
		}

		private function profileEdit():void {
			profileWindow = PopUpManager.createPopUp(header, ProfileEditWindow, true) as
				ProfileEditWindow
			PopUpManager.centerPopUp(profileWindow);
		}

		private function onLoginComplete(event:LoginEvent):void {
			header.memberIdText.text = event.memberId;
			header.currentState = "loggedIn";
		}
	}
}