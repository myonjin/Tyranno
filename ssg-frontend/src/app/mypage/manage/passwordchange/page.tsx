'use client'

import { mchangePasswordAPI } from '@/actions/mypage'
import { useState } from 'react'

export default function PasswordChange() {
    const [password, setpassword] = useState<string>('')
    const [passwordConfirm, setpasswordConfirm] = useState<string>('')
    const handleSubmit = async () => {
        if (password !== passwordConfirm) {
            alert('비밀번호가 일치하지 않습니다.')
            return
        } else {
            const res = await mchangePasswordAPI(password)
            alert(res)
        }
    }
    return (
        <>
            <div className="p-4 flex">
                <div className="w-full">
                    <div className="flex justify-center items-center">
                        <span className="text-sm font-bold w-32 ">새 비밀번호</span>
                        <input
                            type="password"
                            className="w-full h-10 my-2"
                            style={{ border: '1px solid #c6c6c6' }}
                            onChange={(e) => setpassword(e.target.value)}
                        />
                    </div>
                    <div className="flex justify-center items-center">
                        <span className="text-sm font-bold w-32 ">비밀번호 재확인</span>
                        <input
                            type="password"
                            className="w-full h-10 my-2"
                            style={{ border: '1px solid #c6c6c6' }}
                            placeholder="새 비밀번호 확인"
                            onChange={(e) => setpasswordConfirm(e.target.value)}
                        />
                    </div>
                </div>
            </div>
            <div className="flex justify-center my-4">
                <button className="w-32 h-10 bg-[#ff5452] text-white font-bold" onClick={handleSubmit}>
                    비밀번호 변경
                </button>
            </div>
        </>
    )
}
