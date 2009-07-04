package shm.common {
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.core.Application;
	import mx.core.IMXMLObject;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	

	public class UICtrlBase implements IMXMLObject {
		public function UICtrlBase() {
		}

		public function initialized(document:Object, id:String):void {
			var component:UIComponent = document as UIComponent;
			doInitialize(component, id);
			document.addEventListener(FlexEvent.CREATION_COMPLETE, onCreationCompleted);
		}

		protected function doInitialize(component:UIComponent, id:String):void {
		}


		protected function onCreationCompleted(event:FlexEvent):void {
		}

		public function onServiceCallFailed(e:FaultEvent, token:Object = null):void {
			throw new Error(e.message);
		}

		public function onServiceCompleted(event:ResultEvent):void {
			
			var result:Object = event.result;
			var kind:String = result.response.kind;
			switch (kind) {
				case "success":
					doOnServiceSuccess(event);
					break;
				case "failure":
					doOnServiceFailure(event);
					break;
				default:
					throw new Error("変な状態" + kind);
					break;
			}
		}
		
		protected function doOnServiceSuccess(event:ResultEvent):void {
		}
		
		protected function doOnServiceFailure(event:ResultEvent):void {
			showMessages(event.result);
		}

		protected function showMessages(result:Object):void {
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