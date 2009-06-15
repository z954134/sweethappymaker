package kakeibo.entity
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass(alias="kakeibo.entity.User")]
	public class User
	{
		public var id:Number;
		public var userId:String;
		public var name:String;
		public var password:String;
		public var version:int;
		public var itemList:ArrayCollection;
		
		public function toString():String
		{
			return id.toString();
		}
		
		public function equals(obj:Object):Boolean
		{
			if (obj is User)
			{
				var u:User = obj as User;
				return u.id == this.id;	
			}
			return false;
		}
	}
}