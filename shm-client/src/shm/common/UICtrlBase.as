package shm.common
{
	import mx.core.IMXMLObject;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;

	public class UICtrlBase implements IMXMLObject
	{
		public function UICtrlBase()
		{
		}

		public function initialized(document:Object, id:String):void
		{
			var component:UIComponent = document as UIComponent;
			doInitialize(component, id);
			document.addEventListener(FlexEvent.CREATION_COMPLETE, onCreationCompleted);
		}
		
		protected function doInitialize(component:UIComponent, id:String):void {
		}

		
		protected function onCreationCompleted(event:FlexEvent):void {
		}
		
		public function serviceCallFaultHandler(e:FaultEvent, token:Object=null):void {
			throw new Error(e.message);
		}
		
	}
}