'use client'
import { checkAuthCodeAPI, findIdAPI, sendTextAPI } from '@/actions/user'
import Buttons from '@/components/ui/buttons'
import { authCode } from '@/types/UserDataType'
import { useRouter } from 'next/navigation'
import React, { useState } from 'react'
export default function Authphone() {
    const [name, setName] = useState('')
    const [gender, setGender] = useState<number>()
    const [birthday, setBirthday] = useState('')
    const [phoneNumberString, setPhoneNumberString] = useState('')
    const [authCode, setAuthCode] = useState<string>('')
    const [authCodeCheck, setAuthCodeCheck] = useState<boolean>(false)
    const router = useRouter()

    const settingName = (e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value)
    }
    const parsingBirthday = (num: string) => {
        return num
            .replace(/[^0-9]/g, '')
            .replace(/^(\d{0,4})(\d{0,2})(\d{0,2})$/g, '$1-$2-$3')
            .replace(/(\-{1,2})$/g, '')
    }
    const parsingPhoneNumber = (num: string) => {
        return num
            .replace(/[^0-9]/g, '')
            .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, '$1-$2-$3')
            .replace(/(-{1,2})$/g, '')
    }

    const genderHandler = (genderType: number) => {
        setGender(genderType)
    }

    const handleButtonClick = async () => {
        try {
            const data = phoneNumberString.replace(/-/g, '')
            const response = await sendTextAPI(data)
            if (response.statusCode === 2000) {
                alert('인증번호가 발송되었습니다.')
            }
            setAuthCodeCheck(true)
        } catch (error) {
            console.log(error)
        }
    }
    const verifyAuthCode = async () => {
        try {
            const data: authCode = {
                phoneNumber: phoneNumberString.replace(/-/g, ''),
                randomNumber: authCode,
            }
            const response = await checkAuthCodeAPI(data)
            if (response.isSuccess == true) {
                const phone = phoneNumberString
                const response = await findIdAPI(phone)

                if (response.isSuccess === false) {
                    alert(response.message)
                    router.push('/user/findidform')
                } else {
                    localStorage.setItem('findId', response.result)
                    alert('인증 되었습니다')
                    router.push('/user/findpw')
                }
            } else {
                alert('인증번호가 틀렸습니다.')
                router.push('/user/signupintro/auth')
            }
        } catch (error) {
            console.log(error)
        }
    }
    const fieldCheck = () => {
        if (name && birthday && phoneNumberString && gender) {
            return true
        } else {
            return false
        }
    }

    return (
        <>
            <div className="auth_user" style={{ marginTop: '30px' }}>
                <span className="inp_txt">
                    <input
                        className="input-content"
                        type="text"
                        id="userName"
                        name="name"
                        placeholder="이름"
                        onChange={settingName}
                    />
                    <span className="tag-group">
                        <div className={`tag ${gender === 1 ? 'checked' : ''}`} onClick={() => genderHandler(1)}>
                            <input type="radio" name="sex" value="1" />
                            <label>남</label>
                        </div>
                        <div className={`tag ${gender === 2 ? 'checked' : ''}`} onClick={() => genderHandler(2)}>
                            <input type="radio" name="sex" value="2" />
                            <label>여</label>
                        </div>
                    </span>
                </span>
                <span className="inp_txt">
                    <input
                        type="text"
                        className="input-content"
                        value={birthday}
                        placeholder="생년월일 8자리(예. 20100101)"
                        maxLength={10}
                        onChange={(e) => setBirthday(parsingBirthday(e.target.value))}
                    />

                    <select id="currency" name="foriegn" style={{ fontSize: '14px', color: '#000', width: '10%' }}>
                        <option>내국인</option>
                        <option>외국인</option>
                    </select>
                </span>
                {/* </div> */}
                <span className="inp_txt">
                    <label htmlFor="currency" className="" />
                    <select
                        id="currency"
                        name="phone-type"
                        style={{ fontSize: '14px', color: '#000' }}
                        className="h-full rounded-md border-0 bg-transparent py-0 pl-2 pr-7 text-gray-500 focus:ring-2 focus:ring-inset focus:ring-indigo-600 "
                    >
                        <option>SKT</option>
                        <option>KT</option>
                        <option>LG U+</option>
                        <option>SKT 알뜰폰</option>
                        <option>KT 알뜰폰</option>
                        <option>LG U+ 알뜰폰</option>
                    </select>
                </span>
                <span className="inp_txt">
                    <input
                        className="input-content"
                        type="tel"
                        value={phoneNumberString}
                        maxLength={13}
                        onChange={(e) => setPhoneNumberString(parsingPhoneNumber(e.target.value))}
                        placeholder="-없이 휴대폰번호 입력"
                    />
                </span>
            </div>
            {authCodeCheck === true ? (
                <div>
                    <span className="inp_txt">
                        <input
                            className="input-content"
                            type="number"
                            maxLength={6}
                            onChange={(e) => setAuthCode(e.target.value)}
                            placeholder="인증번호 입력"
                        />
                    </span>

                    <div>
                        <button
                            className="button-groups"
                            style={{ backgroundColor: '#ff5452', color: '#fff' }}
                            onClick={verifyAuthCode}
                        >
                            확인
                        </button>
                    </div>
                </div>
            ) : (
                <div>
                    {!fieldCheck() ? (
                        <Buttons
                            title="인증번호 받기"
                            href="/user/signupintro/auth"
                            click={() => alert('모든 항목을 체크해주세요')}
                        />
                    ) : (
                        <div>
                            <button
                                className="button-groups"
                                style={{ backgroundColor: '#ff5452', color: '#fff' }}
                                onClick={handleButtonClick}
                            >
                                인증번호 받기
                            </button>
                        </div>
                    )}
                </div>
            )}
        </>
    )
}
