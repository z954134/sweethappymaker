package shm.okdialy {
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.core.Application;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	
	import shm.common.UICtrlBase;

	public class OkDialyListCtrl extends UICtrlBase {
		private var view:OkDialyList;

		protected function reset(event:Event):void {
			view.listService.send();
		}

		protected override function doInitialize(component:UIComponent, id:String):void {
			view = OkDialyList(component);
		}

		protected override function onCreationCompleted(event:FlexEvent):void {
		}

		public function onLoadButtonClicked(event:MouseEvent):void {
			var e:OkDialyEvent = new OkDialyEvent(OkDialyEvent.LOAD_REQUIRED);
			e.requiredDate = view.dg.selectedItem['dialyDate'];
			view.dispatchEvent(e);
		}
	}
}