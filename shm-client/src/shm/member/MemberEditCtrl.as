package shm.member {
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
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

		public function onServiceCompleted(event:ResultEvent):void {
			var result:Object = event.result;
			var kind:String = result.response.kind;
			switch (kind) {
				case "success":
					view.listService.send();
					break;
				case "failure":
					onFailure(result);
					break;
				default:
					throw new Error("変な状態" + kind);
					break;
			}
		}



		private function onFailure(result:Object):void {
			var dispMsg:String = "";
			var message:Object = result.response.message;
			if (message is ArrayCollection) {
				var msgList:ArrayCollection = ArrayCollection(message);
				for (var i:int; i < msgList.length; i++) {
					dispMsg += msgList.getItemAt(i) + "\n";
				}
			} else {
				dispMsg = message.toString();
			}
			Alert.show(dispMsg, "エラー");
		}
	}
}