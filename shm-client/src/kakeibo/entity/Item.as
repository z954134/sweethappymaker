package kakeibo.entity
{
	[Bindable]
	[RemoteClass(alias="kakeibo.entity.Item")]
	public class Item
	{
		public var id:Number;
		public var userId:Number;
		public var subcategoryId:Number;
		public var purchaseDate:Date;
		public var price:Number = new Number(0);
		public var name:String;
		public var note:String;
		public var version:Number;
		public var subcategory:Subcategory;
		public var user:User;
		
		public function get userName():String {
			return user.name;
		}
		
		public function get subcategoryName():String
		{
			return subcategory.name;
		}
		
		public function get category():Category
		{
			return subcategory.category;
		}
		
		public function get categoryName():String
		{
			return subcategory.categoryName;
		}
	}
}
