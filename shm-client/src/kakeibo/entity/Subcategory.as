package kakeibo.entity
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass(alias="kakeibo.entity.Subcategory")]
	public class Subcategory
	{
		public var id:Number;
		public var categoryId:Number;
		public var name:String;
		public var version:Number;
		public var itemList:ArrayCollection;
		public var category:Category;
		
		public function get categoryName():String
		{
			return category.name;
		}
	}
}