package kakeibo.entity{	import mx.collections.ArrayCollection;	
	[Bindable]
	[RemoteClass(alias="kakeibo.entity.Category")]	public class Category
	{
		public var id:Number;
		public var name:String;
		public var version:Number;
		public var subcategoryList:ArrayCollection;
	}
}
