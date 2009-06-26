package shm.dao;

import javax.jdo.PersistenceManager;

import shm.model.Member;
import shm.model.MemberMeta;

public class MemberDao extends MyGenericDao<Member> {

    private MemberMeta m = new MemberMeta();

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
    public Member getMemberByMemberId(String memberId) {

        Member member = from().where(m.memberId.eq(memberId)).getSingleResult();
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
        Member m = getMemberByMemberId(memberId);
        return m != null;
    }
    
    public String getKeyByMemberId(String memberId) {
        Member m = getMemberByMemberId(memberId);
        return m.getKey();
    }
}
