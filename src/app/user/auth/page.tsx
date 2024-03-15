// 'use client'
// import HeaderTitle from '@/components/ui/HeaderTitle'
// import './terms.css'
// import React, { useState } from 'react'
// import { Checkbox } from '@nextui-org/react'
// function Auth() {
//     const [terms, setTerms] = useState([
//         { id: 1, title: '개인정보 이용 및 제공 동의', checked: false },
//         { id: 2, title: '통신사 이용약관 동의', checked: false },
//         { id: 3, title: '고유식별정보 처리 동의', checked: false },
//         { id: 4, title: '서비스 이용약관 동의', checked: false },
//     ])
//     const [allChecked, setAllChecked] = useState(false)
//     const [checkedItem, setCheckedItem] = useState<number[]>([])
//     const checkItemhandler = (id: number, ischecked: boolean) => {
//         console.log(id, ischecked)
//         if (ischecked) {
//             setCheckedItem((prev) => [...prev, id])
//         } else {
//             setCheckedItem(checkedItem.filter((item) => item !== id))
//         }
//     }
//     const handleAllChecked = (checked: boolean) => {
//         setAllChecked(checked)
//         const updatedTerms = terms.map((item) => ({
//             ...item,
//             checked: checked,
//         }))
//         setTerms(updatedTerms)
//         if (checked) {
//             setCheckedItem(updatedTerms.map((item) => item.id))
//         } else {
//             setCheckedItem([])
//         }
//     }
//     console.log(checkedItem)
//     return (
//         <div>
//             <HeaderTitle title="신세계포인트 통합회원 가입" />
//             <div className="non-header">
//                 {terms.map((item) => (
//                     <SsgChecker key={item.id} item={item} />
//                 ))}
//                 <div className="terms-box">
//                     <label>
//                         <input
//                             type="checkbox"
//                             onChange={() => handleAllChecked(!allChecked)}
//                             checked={checkedItem.length === terms.length ? true : false}
//                         />
//                         <span style={{ padding: '10px', color: 'red' }}>전체 동의</span>
//                     </label>
//                 </div>
//             </div>
//         </div>
//     )
// }

// export default Auth

// export const SsgChecker = (item: any) => {
//     return (
//         <div className="terms-box">
//             <label>
//                 <input type="checkbox" checked={item.checked} />
//                 <span style={{ padding: '10px' }}>{item && item.title}</span>
//             </label>
//             <span className="terms-content">내용 보기</span>
//         </div>
//     )
// }

'use client'
import HeaderTitle from '@/components/ui/HeaderTitle'
import './terms.css'
import React, { useState } from 'react'
import { Checkbox } from '@nextui-org/react'
import Buttons from '@/components/ui/buttons'

function Auth() {
    const [terms, setTerms] = useState([
        { id: 1, title: '개인정보 이용 및 제공 동의' },
        { id: 2, title: '통신사 이용약관 동의' },
        { id: 3, title: '고유식별정보 처리 동의' },
        { id: 4, title: '서비스 이용약관 동의' },
    ])

    const [allChecked, setAllChecked] = useState(false)
    const [checkedItem, setCheckedItem] = useState<number[]>([])
    const checkItemhandler = (id: number, ischecked: boolean) => {
        console.log(id, ischecked)
        if (ischecked) {
            setCheckedItem((prev) => [...prev, id])
        } else {
            setCheckedItem(checkedItem.filter((item) => item !== id))
        }
    }
    const handleAllChecked = (checked: boolean) => {
        setAllChecked(checked)
        const updatedTerms = terms.map((item) => ({
            ...item,
            checked: checked,
        }))
        setTerms(updatedTerms)
        if (checked) {
            setCheckedItem(updatedTerms.map((item) => item.id))
        } else {
            setCheckedItem([])
        }
    }
    console.log(checkedItem)
    return (
        <div>
            <HeaderTitle title="신세계포인트 통합회원 가입" />
            <div className="non-header">
                {terms.map((item) => (
                    <div key={item.id} className="terms-box">
                        <label>
                            <input
                                type="checkbox"
                                key={item.id}
                                onChange={() =>
                                    checkItemhandler(item.id, !checkedItem.includes(item.id) ? true : false)
                                }
                                checked={checkedItem.includes(item.id)}
                            />
                            <span style={{ padding: '10px' }}>{item.title}</span>
                        </label>
                        <span className="terms-content">내용 보기</span>
                    </div>
                ))}
                <div className="terms-box">
                    <label>
                        <input
                            type="checkbox"
                            onChange={() => handleAllChecked(!allChecked)}
                            checked={checkedItem.length === terms.length ? true : false}
                        />
                        <span style={{ padding: '10px', color: 'red' }}>전체 동의</span>
                    </label>
                </div>
                <div className="auth_user">
                    <span className="inp_txt">
                        <input className="input-content" type="text" id="userName" name="name" placeholder="이름" />
                        <div className="tag-group">
                            <input type="radio" name="sex" value="1" />
                            <label>남</label>
                        </div>

                        {/* <label className="userName"></label> */}
                    </span>
                    <div>
                        <input
                            type="text"
                            name="price"
                            id="price"
                            className="block w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                            placeholder="0.00"
                        />

                        <label className="userName"></label>
                    </div>
                    <span>
                        <Buttons title="인증번호 받기" />
                    </span>
                    <div className="m_auth_section">
                        <ul className="noti_list">
                            <li className="text-sm">본인 명의의 휴대폰 정보를 정확히 입력하여 주시기 바랍니다.</li>
                            <li className="text-sm">
                                타인의 명의를 도용하여 부정인증을 시도한 경우 관련 법령에 따라 처벌(3년 이하의 징역형
                                또는 1천만원 이하의 벌금형) 받을수 있습니다.
                            </li>
                            <li className="text-sm">인증문의 : (주)KCB고객센터(02-708-1000)</li>
                        </ul>
                    </div>
                </div>
                <span className="inp_txt">
                    <input className="input-content" type="text" id="userName" name="name" placeholder="이름" />

                    <label className="userName"></label>
                </span>
                <span className="inp_txt">
                    <input
                        className="input-content"
                        type="text"
                        id="userName"
                        name="name"
                        placeholder="-없이 휴대폰번호 입력"
                    />

                    <label className="userName"></label>
                </span>
            </div>
        </div>
    )
}

export default Auth
