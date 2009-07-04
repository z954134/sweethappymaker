package shm {
	import flash.events.MouseEvent;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;

	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

	import shm.common.UICtrlBase;

	public class HeaderCtrl extends UICtrlBase {

		private var header:Header;

		protected override function doInitialize(component:UIComponent, id:String):void {
			header = Header(component);
		}


		protected override function onCreationCompleted(event:FlexEvent):void {
			header.loginUserService.send();
			header.loginUrlService.send();
			header.logoutUrlService.send();
		}

		public function onLoginUserLoaded(event:ResultEvent):void {
			header.loginUserText.text = event.result.loginUser;
			if (isLoggedIn()) {
				header.currentState = "loggedIn";
			} else {
				header.currentState = "notLoggedIn";
			}
		}

		public function onLoginOutButttonClicked(event:MouseEvent):void {
			if (isLoggedIn()) {
				logout();
			} else {
				login();
			}
		}


		private function login():void {
			var url:String = header.urlModel.login;
			navigate(url);
		}

		private function logout():void {
			var url:String = header.urlModel.logout;
			navigate(url);
		}

		private function navigate(url:String):void {
			navigateToURL(new URLRequest(url), "_self");
		}

		private function isLoggedIn():Boolean {
			return header.loginUserText.text != "GUEST";
		}
	}
}