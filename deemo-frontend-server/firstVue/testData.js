let test = [{
        topicType: 'singleChoice',
        topicTypeName: '第一部分：单选题',
        questionList: [{
                questiontitle: '1.【Task Queue】工作序列中的All, Today, Week和Date Range是基于以下哪个时间对申请进行搜索：',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: 'Day In'
                    },
                    {
                        selectItem: 'B',
                        answer: 'Day Out'
                    },
                    {
                        selectItem: 'C',
                        answer: 'Assignment Time'
                    },
                    {
                        selectItem: 'D',
                        answer: 'Update Time'
                    }
                ],
                answer: '',
                modelAnswer: 'A'
            },
            {
                questiontitle: '2.	【Documents】关于Document 界面中，以下说法不正确的是：',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: '勾选“一致且完整（Consistent & Completed）”勾选框之前，RCO可以上传文档'
                    },
                    {
                        selectItem: 'B',
                        answer: '勾选“一致且完整（Consistent & Completed）”勾选框之后，RCO可以上传文档'
                    },
                    {
                        selectItem: 'C',
                        answer: '勾选“一致且完整（Consistent & Completed）”勾选框之后，RCO可以取消勾选任何一个文档的已验证勾选框'
                    },
                    {
                        selectItem: 'D',
                        answer: '勾选“一致且完整（Consistent & Completed）”勾选框之后，RCO不可以取消勾选任何一个文档的已验证勾选框'
                    }
                ],
                answer: [],
                modelAnswer: 'D'
            },
            {
                questiontitle: '3.【Fraud】RCO在什么情况下不能勾选可疑信息（Suspicious）？',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: '勾选“一致且完整（Consistent & Completed）”之前'
                    },
                    {
                        selectItem: 'B',
                        answer: '勾选“一致且完整（Consistent & Completed）”之后，但未给出批复结果之前'
                    },
                    {
                        selectItem: 'C',
                        answer: '做完家访以后'
                    },
                    {
                        selectItem: 'D',
                        answer: '批复完该申请之后'
                    }
                ],
                answer: [],
                modelAnswer: 'D'
            },
            {
                questiontitle: '4.	【Summary】关于申请件编号命名规则描述正确的选项是：',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: '申请号是由随机的动态数字产生并保证不重复'
                    },
                    {
                        selectItem: 'B',
                        answer: '申请号是由前两位固定的字母+18位随机的动态数字组成并保证不重复'
                    },
                    {
                        selectItem: 'C',
                        answer: '申请号是从系统预先存储的号码池中随机选取的'
                    },
                    {
                        selectItem: 'D',
                        answer: '申请号是由前两位固定的字母 + 金融公司及经销商编码 + 6位的日期编码 + 4位递增的申请编号 + 2位版本编号 '
                    }
                ],
                answer: [],
                modelAnswer: 'D'
            },
            {
                questiontitle: '5.【NCIIC】当申请人为大陆居民且姓名、身份证号码和性别均一致，NCIIC返回的结果为：',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: '一致'
                    },
                    {
                        selectItem: 'B',
                        answer: '不一致'
                    },
                    {
                        selectItem: 'C',
                        answer: '不适用'
                    },
                    {
                        selectItem: 'D',
                        answer: '以上都不正确'
                    }
                ],
                answer: [],
                modelAnswer: 'A'
            },
            {
                questiontitle: '6.	【Exposure】在风险敞口页签详情（Detail Section）部分中，当前申请（Current Application）以（）背景色高亮显示。',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: '黄色'
                    },
                    {
                        selectItem: 'B',
                        answer: '绿色'
                    },
                    {
                        selectItem: 'C',
                        answer: '红色'
                    },
                    {
                        selectItem: 'D',
                        answer: '蓝色'
                    }
                ],
                answer: [],
                modelAnswer: 'B'
            },
            {
                questiontitle: `7.	【Exposure】申请1：在MBAFC的风险敞口为200,000, 申请状态为Condition Placed；<br>
                申请2：在MBAFC的风险敞口为300,000, 申请状态为Condition Submitted；<br>
                申请3：在MBAFC的风险敞口为400,000, 申请状态为New；<br>
                若当前申请在风险敞口详情部分共有以上三条申请记录，当分析师进入申请3的风险敞口页签点击重新计算按钮后，MBAFC栏的Applications/Pipeline Exposure为（）。
                `,
                questionAnswer: [{
                        selectItem: 'A',
                        answer: '20,0000/40,0000'
                    },
                    {
                        selectItem: 'B',
                        answer: '20,0000/70,0000'
                    },
                    {
                        selectItem: 'C',
                        answer: '50,0000/40,0000'
                    },
                    {
                        selectItem: 'D',
                        answer: '90,0000/0'
                    }
                ],
                answer: [],
                modelAnswer: 'D'
            },
            {
                questiontitle: '8.【HV】以下关于HV说法错误的是：',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: '可以一次申请对多人和多个地址进行家访'
                    },
                    {
                        selectItem: 'B',
                        answer: '发出HV申请后需等待HV完成后方可继续进行审批'
                    },
                    {
                        selectItem: 'C',
                        answer: '可以通过点击Waive取消HV申请'
                    },
                    {
                        selectItem: 'D',
                        answer: '发出HV申请后RCO可以对案件进行初审，但不可进行终审'
                    }
                ],
                answer: [],
                modelAnswer: 'D'
            },
            {
                questiontitle: '9.	【PV】RCO 在PV里添加的Comments会同步到哪个界面内？',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: '会同步到Diary and Notes 里'
                    },
                    {
                        selectItem: 'B',
                        answer: '会同步到DTS 界面里 '
                    },
                    {
                        selectItem: 'C',
                        answer: '会同步到HV界面里'
                    },
                    {
                        selectItem: 'D',
                        answer: '会同步到Decision 界面里'
                    }
                ],
                answer: [],
                modelAnswer: 'D'
            },
            {
                questiontitle: '10.	【Decision】关于刷新Exposure，如下说明正确的是：',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: 'RCO进行终审时，如果Exposure发生变更，RCO点击Submit Button后系统会自动刷新Exposure和初审/终审建议'
                    },
                    {
                        selectItem: 'B',
                        answer: 'RCO进行终审时，如果Exposure发生变更，系统会在点击Submit Button前自动刷新Exposure和初审/终审建议'
                    },
                    {
                        selectItem: 'C',
                        answer: 'RCO进行终审时，如果Exposure发生变更，RCO点击Save Button后系统会自动刷新Exposure和初审/终审建议'
                    },
                    {
                        selectItem: 'D',
                        answer: 'RCO进行终审时，如果Exposure发生变更，RCO点击Refresh Button后系统会刷新Exposure和初审/终审建议'
                    }
                ],
                answer: [],
                modelAnswer: 'A'
            },
            {
                questiontitle: '11.	【Decision】以下哪种情况下可以打开Decision界面？',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: '未勾选Consistent & Completed勾选框，已勾选NCIIC Checked勾选框'
                    },
                    {
                        selectItem: 'B',
                        answer: '已勾选Consistent & Completed勾选框，未勾选NCIIC Checked勾选框'
                    },
                    {
                        selectItem: 'C',
                        answer: '未勾选Consistent & Completed勾选框，未勾选NCIIC Checked勾选框'
                    },
                    {
                        selectItem: 'D',
                        answer: '已勾选Consistent & Completed勾选框，已勾选NCIIC Checked勾选框'
                    }
                ],
                answer: [],
                modelAnswer: 'D'
            },
            {
                questiontitle: '12.	【Status】条批添加两个条件，Condition submitted后一个条件状态变成Fulfilled，另一个条件状态仍为Pending，则此申请在系统中的状态为：',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: 'Conditioned'
                    },
                    {
                        selectItem: 'B',
                        answer: 'Condition Submitted'
                    },
                    {
                        selectItem: 'C',
                        answer: 'Condition Placed'
                    },
                    {
                        selectItem: 'D',
                        answer: 'In process with FC'
                    }
                ],
                answer: [],
                modelAnswer: 'A'
            },

        ]
    },
    {
        topicType: 'multiChoice',
        topicTypeName: '第二部分：多选题',
        questionList: [{
                questiontitle: '13.	【NCIIC】关于Customer界面中的“NCIIC Checked”勾选框的说法，以下正确的是：',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: '系统可以根据NCIIC查询结果自动勾选并且无法手动编辑'
                    },
                    {
                        selectItem: 'B',
                        answer: '系统可以根据NCIIC查询结果自动勾选并且可以手动编辑'
                    },
                    {
                        selectItem: 'C',
                        answer: 'RCO可以手动勾选，勾选保存后仍然可以再次编辑'
                    },
                    {
                        selectItem: 'D',
                        answer: 'RCO可以手动勾选，但勾选保存后无法再进行编辑'
                    }
                ],
                answer: [],
                modelAnswer: ['A', '', '', 'D']
            },
            {
                questiontitle: '14.	【Task Queue】申请在哪些状态下可以在CAP里被withdraw？',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: 'New'
                    },
                    {
                        selectItem: 'B',
                        answer: 'Resubmitted'
                    },
                    {
                        selectItem: 'C',
                        answer: 'Approved'
                    },
                    {
                        selectItem: 'D',
                        answer: 'Received'
                    },
                    {
                        selectItem: 'E',
                        answer: 'Activated'
                    }
                ],
                answer: [],
                modelAnswer: ['A', 'B', 'C', '']
            },
            {
                questiontitle: '15.	【Documents】关于“Consistent & Completed”勾选框，以下说法不正确的是：',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: '在勾选Consistent & Completed勾选框之前，申请可以退回FC修改'
                    },
                    {
                        selectItem: 'B',
                        answer: '在勾选Consistent & Completed勾选框之后，申请可以退回FC修改'
                    },
                    {
                        selectItem: 'C',
                        answer: '在勾选Consistent & Completed勾选框之前，RCO可以直接拒绝申请'
                    },
                    {
                        selectItem: 'D',
                        answer: '在勾选Consistent & Completed勾选框之后，RCO可以直接拒绝申请'
                    }
                ],
                answer: [],
                modelAnswer: ['', 'B', 'C', '']
            },
            {
                questiontitle: '16.	【Summary】RCO在CAP系统中手动调整收入和负债，以下说法正确的是：',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: 'RCO手动调整收入后，系统会重新计算DBR'
                    },
                    {
                        selectItem: 'B',
                        answer: 'RCO手动调整收入后，对现有的DBR没有影响'
                    },
                    {
                        selectItem: 'C',
                        answer: 'RCO手动调整负债后，系统会重新计算DBR'
                    },
                    {
                        selectItem: 'D',
                        answer: 'RCO手动调整负债后，对现有的DBR没有影响'
                    }
                ],
                answer: [],
                modelAnswer: ['A', '', '', 'D']
            },
            {
                questiontitle: '17.	【Exposure】以下哪种合同状态会算进Retail Active Exposure:',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: 'Current'
                    },
                    {
                        selectItem: 'B',
                        answer: 'Overdue'
                    },
                    {
                        selectItem: 'C',
                        answer: 'Payout'
                    },
                    {
                        selectItem: 'D',
                        answer: 'Flat Cancel'
                    }, {
                        selectItem: 'E',
                        answer: 'Cancel'
                    }, {
                        selectItem: 'F',
                        answer: 'Write Off'
                    }
                ],
                answer: [],
                modelAnswer: ['A', 'B', '', '']
            },
            {
                questiontitle: '18.	【Decision】以下哪些情况下无法打开Decision界面？',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: 'HV未完成'
                    },
                    {
                        selectItem: 'B',
                        answer: 'Consistent &Completed 勾选框未勾选'
                    },
                    {
                        selectItem: 'C',
                        answer: '未对申请人进行PV'
                    },
                    {
                        selectItem: 'D',
                        answer: '未查看文档'
                    }
                ],
                answer: [],
                modelAnswer: ['A', 'B', '', '']
            },
            {
                questiontitle: '19.	【Dairy & Notes】系统会对以下哪些操作生成Dairy & Notes？',
                questionAnswer: [{
                        selectItem: 'A',
                        answer: 'A.	RCO发送HV申请'
                    },
                    {
                        selectItem: 'B',
                        answer: 'RCO调整申请人收入'
                    },
                    {
                        selectItem: 'C',
                        answer: 'RCO在DTS中勾选Suspicious勾选框'
                    },
                    {
                        selectItem: 'D',
                        answer: '系统自动Call Blaze'
                    }
                ],
                answer: [],
                modelAnswer: ['A', 'B', 'C', 'D']
            }
        ]
    }

]
export default test;