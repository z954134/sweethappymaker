package shm.okdialy {
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	
	import shm.common.UICtrlBase;
	

	public class OkDialyCtrl extends UICtrlBase {
		private var view:OkDialy;

		public function OkDialyCtrl() {
		}

		protected override function doInitialize(component:UIComponent, id:String):void {
			view = OkDialy(component);
		}

		protected override function onCreationCompleted(event:FlexEvent):void {
			view.okForm.initialize();
			view.okList.addEventListener(OkDialyEvent.LOAD_REQUIRED, onLoadRequired);
		}

		private function onLoadRequired(event:OkDialyEvent):void {
			view.okTab.selectedChild = view.okForm;
			// TODO イベントの伝播に失敗するので無理やり
			view.okForm.ctrl.onLoadRequired(event);
		}
	}
}