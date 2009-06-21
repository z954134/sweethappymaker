package shm.okdialy
{
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	
	import shm.common.UICtrlBase;

	public class OkDialyListCtrl extends UICtrlBase
	{
		private var view:OkDialyList;
		
		public function OkDialyListCtrl()
		{
			super();
		}
		
		protected override function doInitialize(component:UIComponent, id:String):void {
			view = OkDialyList(component);
		}

		
		protected override function onCreationCompleted(event:FlexEvent):void {
			
		}
		
		
	}
}