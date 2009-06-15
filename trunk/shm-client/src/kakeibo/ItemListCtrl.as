package kakeibo
{
	import kakeibo.entity.Item;
	import kakeibo.utils.UICtrlBase;
	import kakeibo.utils.UIUtils;
	
	import mx.collections.ArrayCollection;
	import mx.controls.DataGrid;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.AsyncResponder;
	import mx.rpc.AsyncToken;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	public class ItemListCtrl extends UICtrlBase
	{
		
		private var view:ItemList;
		
		
		public var itemEdit:ItemEdit;
		
		/** 初期化時のコールバック */ 
		protected override function doInitialize(ui:UIComponent, id:String):void
		{
			view = ItemList(ui);
		
		}
		/** 生成完了時のコールバック */
		protected override function onCreationCompleted(event:FlexEvent):void
		{
			view.itemGrid.addEventListener(ListEvent.CHANGE, onItemGridChanged);
			view.itemGrid.addEventListener(ListEvent.CHANGE, itemEdit.ctrl.onItemGridChanged);
			loadItemList();
		}
		
		public function loadItemList():void
		{
			var itemService:RemoteObject = new RemoteObject("itemService");
			var token:AsyncToken = itemService.findAll();
			token.addResponder(new AsyncResponder(
				function (e:ResultEvent, o:Object):void
				{
					view.items.itemList = e.result as ArrayCollection;
				}, serviceCallFaultHandler));
		}

		/** グリッド変更時 */
		public function onItemGridChanged(event:ListEvent):void
		{
			var grid:DataGrid = event.target as DataGrid;
			changeStatus(grid);
		}
		
		private function changeStatus(grid:DataGrid):void
		{
			if (grid.selectedItem != null)
			{
				view.currentState = "Selected";
			}
		}
		
		public function onDeleteButtonClicked():void
		{
			UIUtils.executeIfOk(view, doDelete,
				"選択したレコードを削除してもよろしいですか？");
		}
		
		private function doDelete():void
		{
			
		}
	}
}