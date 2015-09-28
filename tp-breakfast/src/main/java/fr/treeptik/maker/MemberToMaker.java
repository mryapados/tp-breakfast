package fr.treeptik.maker;

import fr.treeptik.entity.Member;

public class MemberToMaker {

	public static MemberMaker to(Member member){
		MemberMaker memberMaker = new MemberMaker();
		
		memberMaker.setId(member.getId());
		memberMaker.setLogin(member.getLogin());
		
		memberMaker.setEnabled(member.getEnabled());
		memberMaker.setRole(member.getRole());
		
		memberMaker.setFirstName(member.getFirstName());
		memberMaker.setLastName(member.getLastName());
		memberMaker.setPreference(member.getPreference());

		return memberMaker;
	}
	
	
	
}
