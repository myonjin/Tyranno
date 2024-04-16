'use client'
import { useEffect, useState } from 'react'
import Link from 'next/link'
import constraints from '@/actions/constraints'

export interface CategoryMiddleType {
    middleId: string
    middleName: string
}
function CategoryModal({ largeId }: { largeId: string }) {
    const [categoryMiddle, setCategoryMiddle] = useState<CategoryMiddleType[]>([] as CategoryMiddleType[])

    useEffect(() => {
        const GetCategoryMiddle = async () => {
            const data = await fetch(`${constraints.Server_Url}/api/v1/category/middle/${largeId}`)
            if (data) {
                const response = await data.json()
                setCategoryMiddle(response)
            }
        }

        GetCategoryMiddle()
    }, [largeId])

    return (
        <section className="grid grid-cols-2 gap-x-4 ">
            <Link href={`/category/${largeId}`} className="flex text-xs min-h-[38px] pl-3 pr-[13px]">
                전체보기
            </Link>
            {categoryMiddle &&
                categoryMiddle.map((opt: CategoryMiddleType, idx) => (
                    <div key={idx}>
                        <ul className="flex text-xs min-h-[38px] pl-3 pr-[13px]">
                            <li className="min-h-[38px]">
                                <Link
                                    href={{
                                        pathname: `/productlist`,
                                        query: {
                                            largeId: largeId,
                                            middleId: opt.middleId,
                                        },
                                    }}
                                    passHref
                                >
                                    <div>{opt.middleName}</div>
                                </Link>
                            </li>
                        </ul>
                    </div>
                ))}
        </section>
    )
}
export default CategoryModal
