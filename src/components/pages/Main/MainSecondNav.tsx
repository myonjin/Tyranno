import { secondNavData } from '@/lib/MainSecondNavData'
import Link from 'next/link'

export default function MainSecondNav() {
    return (
        <nav className="sticky  left-0 top-0  bg-white z-10">
            <ul className="flex p-4  justify-between overflow-x-scroll " style={{ whiteSpace: 'nowrap' }}>
                {secondNavData.map((navs) => {
                    return (
                        <div className="">
                            <li key={navs.id} className="mr-5  ">
                                <Link href={navs.url}>
                                    <div className="relative items-center ">
                                        <span className=" ">{navs.name}</span>
                                    </div>
                                </Link>
                            </li>
                        </div>
                    )
                })}
            </ul>
        </nav>
    )
}
