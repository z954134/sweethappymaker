package shm.member {
	import flash.events.MouseEvent;


	import mx.core.IMXMLObject;
	import mx.events.CloseEvent;
	import mx.events.FlexEvent;
	import mx.managers.PopUpManager;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.validators.Validator;

	import shm.common.UIUtils;

	public class MemberEditWindowCtrl implements IMXMLObject {
		
		private var member_:Member;
		
		private var view:MemberEditWindow;
		
		public function set member(m:Member):void {
			member_ = m;
			view.memberIdField.text = m.memberId;
			view.passwordField.text = m.password;
			view.emailField.text = m.email;
		}

		public function initialized(document:Object, id:String):void {
			view = document as MemberEditWindow;
			view.addEventListener(FlexEvent.CREATION_COMPLETE, creationCompleteHandler);
		}

		public function creationCompleteHandler(event:FlexEvent):void {
			view.executeButton.addEventListener(MouseEvent.CLICK, executeButtonClickHandler);
			view.cancelButton.addEventListener(MouseEvent.CLICK, cancelButtonClickHandler);
			view.addEventListener(CloseEvent.CLOSE, closeButtonClickHandler);
		}

		public function executeButtonClickHandler(event:MouseEvent):void {
			var validationResults:Array = Validator.validateAll(view.validators);
			if (validationResults.length > 0) {
				return;
			}

			switch (view.currentState) {
				case "Insert":
					UIUtils.executeIfOk(view, doInsert, "この記録を追加してもよろしいですか？");
					break;
				case "Update":
					UIUtils.executeIfOk(view, doUpdate, "この記録を訂正してもよろしいですか？");
					break;
				case "Delete":
					UIUtils.executeIfOk(view, doDelete, "この記録を削除してもよろしいですか？");
					break;
				default:
					throw new Error("currentState invalid. state=[" + view.currentState +
						"]");
			}
		}

		public function cancelButtonClickHandler(event:MouseEvent):void {
			removePopUp();
		}

		public function closeButtonClickHandler(event:CloseEvent):void {
			removePopUp();
		}



		private function doInsert():void {
			var newMember:Member = new Member();
			newMember.memberId = view.memberIdField.text;
			newMember.email = view.emailField.text;
			newMember.password = view.passwordField.text;
//
//			var token:AsyncToken = userService.insert(newUser);
//			token.addResponder(new AsyncResponder(resultHandler, serviceCallFaultHandler));
		}

		private function doUpdate():void {
//			var token:AsyncToken = userService.update(user_);
//			token.addResponder(new AsyncResponder(resultHandler, serviceCallFaultHandler));
		}

		private function doDelete():void {
//			var token:AsyncToken = userService.deleteUser(user_);
//			token.addResponder(new AsyncResponder(resultHandler, serviceCallFaultHandler));
		}

		private function resultHandler(e:ResultEvent, token:Object = null):void {
			removePopUp();
			view.dispatchEvent(new RecordUpdateWindowEvent(RecordUpdateWindowEvent.UPDATE_COMPLETE));
		}

		private function serviceCallFaultHandler(e:FaultEvent, token:Object = null):void {
			view.memberIdField.errorString = "このユーザIDは既に存在しています。"
		}

		private function removePopUp():void {
			PopUpManager.removePopUp(view);
		}
	}
}