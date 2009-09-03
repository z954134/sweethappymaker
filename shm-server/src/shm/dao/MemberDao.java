package shm.dao;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.users.User;

import shm.model.Member;
import shm.meta.MemberMeta;

public class MemberDao extends MyGenericDao<Member> {

    private MemberMeta m = new MemberMeta();

    public MemberDao() {
        super(Member.class);
    }
    
    public MemberDao(PersistenceManager pm) {
        super(Member.class, pm);
    }

    /**
     * メンバーIDからメンバーを取得する。
     * 
     * @param memberId
     *            メンバーID
     * @return メンバー
     */
    public Member findMember(String memberId) {
        Member member = from().where(m.memberId.eq(memberId)).getSingleResult();
        return member;
    }
    
    public Member findMember(User user) {
        Member member = from().where(m.user.eq(user)).getSingleResult();
        return member;
    }

    /**
     * 指定されたメンバーIDが存在するか判定する。
     * 
     * @param memberId
     *            メンバーID
     * @return 判定結果
     */
    public boolean exists(String memberId) {
        Member m = findMember(memberId);
        return m != null;
    }
    
    public boolean exists(User user) {
        Member m = findMember(user);
        return m != null;
    }
    
    
    public String getKeyByMemberId(String memberId) {
        Member m = findMember(memberId);
        return m.getKey();
    }
}
